package io.swagger.api;

import com.yahoo.athenz.auth.PrivateKeyStore;
import com.yahoo.athenz.common.server.util.ConfigProperties;
import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.PropertiesConfigurationManager;
import org.eclipse.jetty.deploy.bindings.DebugListenerBinding;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenJettyContainer {

    private static String ROOT_DIR;
    private PrivateKeyStore keyStore = null;
    private final Server server;
    private final JettyProperties jettyProperties;
    private final String respondHostName;
    private final String listenHostName;
    private static final String HTTPS_CONNECTOR = "HTTPS_CONNECTOR";
    private static final String STATUS_CONNECTOR = "STATUS_CONNECTOR";

    private final int httpPort;
    private final int httpsPort;
    private final int statusPort;
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public OpenJettyContainer() throws Exception {

        // load jetty and ums properties
        final String jettyPropertyFilePath = System.getProperty("ums.jetty_property_file","src/execution/jetty.properties");
        final InputStream jettyPropertiesInStream = Files.newInputStream(Paths.get(jettyPropertyFilePath));
        jettyProperties = JettyProperties.loadJettyProps(jettyPropertiesInStream);

        final String propFile = System.getProperty("ums.jetty_property_file",
                getRootDir() + "src/execution/ums.properties");
        ConfigProperties.loadProperties(propFile);

       // this.keyStore = UMSImpl.getPrivateKeyStore(UMSImpl.getServerHostName());

        // Retrieve http and https port numbers.
        httpPort = 8082;//jettyProperties.getHttpPort();
        httpsPort = 4443;//jettyProperties.getHttpsPort();

        statusPort = 8083;//jettyProperties.getStatusPort();

        respondHostName = getServerHostName();
        listenHostName = jettyProperties.getListenHost();

        server = buildJettyServer();

        //Add handlers
        final HandlerCollection mainHandler = new HandlerCollection();

        // Disable the "OPTIONS" and "TRACE" HTTP methods.
        mainHandler.addHandler(buildDisableHttpMethodsHandler());

        // Handle re-write rules:  keep-alive, and "Host" response header.
       // mainHandler.addHandler(buildRewriteHandler());

        // Handle servlets: /ums, health-checks.
        final ContextHandlerCollection contextHandlers = buildContextHandler();
        mainHandler.addHandler(contextHandlers);

        // Access log handler
       // mainHandler.addHandler(buildRequestLogHandler());

        server.setHandler(mainHandler);

        // Handle Web-Applications (ums currently doesn't have these...)
        initDeploymentManager(contextHandlers);

        listenOnConfiguredPorts();

    }
    public static void main(final String [] args) throws Exception {

        final OpenJettyContainer umsJettyContainer = new OpenJettyContainer();
        umsJettyContainer.startServer(null);
    }

    private Server buildJettyServer() {
        final Server builtServer = new Server(createThreadPool());
        builtServer.addBean(new ScheduledExecutorScheduler());
        builtServer.setDumpAfterStart(false);
        builtServer.setDumpBeforeStop(false);
        builtServer.setStopAtShutdown(true);

        // Don't show StackTrace in the default ErrorHandler
        final org.eclipse.jetty.server.handler.ErrorHandler noShowStacksErrorHandler = new org.eclipse.jetty.server.handler.ErrorHandler();
        noShowStacksErrorHandler.setShowStacks(false);
        builtServer.setErrorHandler(noShowStacksErrorHandler);

        return builtServer;
    }

    public void startServer(@Nullable final Runnable serverReadyCallback) throws Exception {

        try {
            server.start();
            LOG.info("Jetty server running." +
                    "   listen-hostname: " + ((listenHostName == null) ? "0.0.0.0" : listenHostName) +
                    "   http-port: " + ((httpPort == 0) ? "N/A" : httpPort) +
                    "   https-port: " + ((httpsPort == 0) ? "N/A" : httpsPort) +
                    "   status-port: " + ((statusPort == 0) ? "N/A" : statusPort) +
                    "   response-host-header: " + respondHostName);
            if (serverReadyCallback != null) {
                serverReadyCallback.run();
            }
            server.join();
        } catch (final Exception exception) {
            // log that we are shutting down, re-throw the exception
            LOG.error("Startup failure. Shutting down: ", exception);
            throw exception;
        }
    }

    /** The service's hostname is taken from config, or from machine's hostname */
    private static String getServerHostName() {
        try {
            final InetAddress localhost = InetAddress.getLocalHost();
            return localhost.getCanonicalHostName();
        } catch (final java.net.UnknownHostException e) {
            LOG.info("Unable to determine local hostname: ", e);
            return "localhost";
        }
    }

    /** Create a thread-pool for requests handling */
    private QueuedThreadPool createThreadPool() {
        final QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(100);//jettyProperties.getHttpMaxThreads());
        return threadPool;
    }

    public static String getRootDir() {
        if (ROOT_DIR == null) {
            //ROOT_DIR = System.getProperty("athenz.root_dir", "/home/athenz");
            ROOT_DIR = System.getProperty("athenz.root_dir", "");
        }

        return ROOT_DIR;
    }

    /** Build a handler that disable the "OPTIONS" and "TRACE" HTTP methods */
    private AbstractHandler buildDisableHttpMethodsHandler() {
        final List<String> disableHttpMethods = jettyProperties.getDisableHttpMethodsCsv();
        return new AbstractHandler() {
            @Override
            public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
                if (disableHttpMethods.contains(request.getMethod())) {
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                }
            }
        };
    }

    /**
     * Build handler collections of ums servlet, filters and health checks
     * We have 2 ServletContextHandler
     * The first listen to 4443 only
     *  --> contains ALL the handlers excludes health check handlers
     *
     *  The second listen to 8443 only
     *  --> contains ONLY health check handler
     */
    private ContextHandlerCollection buildContextHandler() throws Exception {
        final ContextHandlerCollection contextHandlers = new ContextHandlerCollection();

        // We don't use session management nor jetty's security handlers:
        final ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS | ServletContextHandler.NO_SECURITY);
        servletContextHandler.setContextPath("/");
        servletContextHandler.setVirtualHosts(new String[]{"@" + HTTPS_CONNECTOR});

        // Build a servlet-handler for the path "/ums" using ServletContainer.
        final ServletHolder servletHolder = new ServletHolder(new ServletContainer(getUMS()));
        servletHolder.setAsyncSupported(true);
        servletContextHandler.addServlet(servletHolder, "/v1/*");

        //Add AthenzQoSFilter filter
        //addAthenzQosFilter(servletContextHandler);

        // Add health-check handlers
       // final ServletContextHandler healthCheckContextHandler = buildHealthCheckContextHandler();

       // contextHandlers.addHandler(healthCheckContextHandler);
        contextHandlers.addHandler(servletContextHandler);

        return contextHandlers;
    }

    protected ResourceConfig getUMS() {
        //return null;
//        ResourceConfig config = new ResourceConfig();
//        config.packages("io.swagger.api");
//        return config;
        return new YANIS();
    }

    /** Handle Web-Applications */
    private void initDeploymentManager(final ContextHandlerCollection contextHandlers) throws Exception {

        final DeploymentManager deploymentManager = new DeploymentManager();

        // If so configured, add debug-mode behaviours to jetty.
        if (jettyProperties.getDebug()) {
            final DebugListener debugListener = new DebugListener(System.err, true, true, true);
            server.addBean(debugListener);
            deploymentManager.addLifeCycleBinding(new DebugListenerBinding(debugListener));
        }

        deploymentManager.setContexts(contextHandlers);
        deploymentManager.setContextAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/servlet-api-[^/]*\\.jar$");

        deploymentManager.addAppProvider(buildWebAppProvider());
        server.addBean(deploymentManager);
    }
    /** Build a WebAppProvider that automatically loads WAR files into the server */
    private WebAppProvider buildWebAppProvider() throws Exception {
        final WebAppProvider webAppProvider = new WebAppProvider();
        webAppProvider.setMonitoredDirName(getJettyFileFromConfig(jettyProperties.getWebappsFolder(), true).getPath());
        webAppProvider.setScanInterval(60);
        webAppProvider.setExtractWars(true);
        webAppProvider.setConfigurationManager(new PropertiesConfigurationManager());
        webAppProvider.setParentLoaderPriority(true);
        webAppProvider.setTempDir(getJettyFileFromConfig(jettyProperties.getTempFolder(), true));
        webAppProvider.setDefaultsDescriptor(getJettyFileFromConfig(jettyProperties.getWebdefaultXmlFile(), false).getAbsolutePath());
        return webAppProvider;
    }

    /**
     * Get a File object using a path that is specified in configuration.
     * If the path doesn't exists - an exception is thrown (this method attempts to create folder-paths).
     */
    private File getJettyFileFromConfig(final String path, final boolean isFolder) throws Exception {
        final File file = new File(path);
        if (isFolder) {
            file.mkdirs();
            if (!file.isDirectory()) {
                throw new IOException("Failed to open directory in path: " + path);
            }
        } else {
            if (!file.isFile()) {
                throw new IOException("Failed to open file in path: " + path);
            }
        }
        return file;
    }

    /** Listen on all configured ports */
    private void listenOnConfiguredPorts() throws Exception {
        final HttpConfiguration httpConfig = newHttpConfiguration();

        final int idleTimeout = jettyProperties.getHttpIdleTimeout();

        // Listen to HTTP port (if configured).
        if (httpPort > 0) {
            listenOnPort(httpConfig, httpPort, idleTimeout, null);
        }

        // Listen to HTTPS port (if configured).
        if (httpsPort > 0) {
            final SslContextFactory sslContextFactory = createSSLContextFactory(true);
            listenOnPort(httpConfig, httpsPort, idleTimeout, sslContextFactory);
        }

        // Listen to STATUS port (if configured - and only if it's different from HTTP/HTTPS).
        if ((statusPort > 0) && (statusPort != httpPort) && (statusPort != httpsPort)) {
            if (httpsPort > 0) {
                final SslContextFactory sslContextFactory = createSSLContextFactory(false);
                listenOnPort(httpConfig, statusPort, idleTimeout, sslContextFactory);
            } else if (httpPort > 0) {
                listenOnPort(httpConfig, statusPort, idleTimeout, null);
            }
        }
    }

    /** Create an empty HttpConfiguration with basic initialization */
    private HttpConfiguration newHttpConfiguration() {
        final HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setOutputBufferSize(jettyProperties.getHttpOutputBufferSize());
        httpConfig.setRequestHeaderSize(jettyProperties.getHttpRequestHeaderSize());
        httpConfig.setResponseHeaderSize(jettyProperties.getHttpResponseHeaderSize());
        httpConfig.setSendServerVersion(jettyProperties.getHttpSendServerVersion());
        httpConfig.setSendDateHeader(jettyProperties.getHttpSendDateHeader());
        return httpConfig;
    }

    private void listenOnPort(HttpConfiguration httpConfig, final int port, final int idleTimeout, @Nullable final SslContextFactory sslContextFactory) {

        final ConnectionFactory[] sslConnectionFactories;
        if (sslContextFactory == null) {
            sslConnectionFactories = new ConnectionFactory[]{
                    new HttpConnectionFactory(httpConfig)
            };
        } else {
            httpConfig = new HttpConfiguration(httpConfig);
            httpConfig.setSecureScheme("https");
            httpConfig.setSecurePort(port);
            httpConfig.addCustomizer(new SecureRequestCustomizer());
            sslConnectionFactories = new ConnectionFactory[]{
                    new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                    new HttpConnectionFactory(httpConfig)
            };
        }

        final int acceptQueueSize = jettyProperties.getAcceptQueueSize();
        LOG.info("Using Accept queue size: {}", acceptQueueSize);

        final ServerConnector connector = new ServerConnector(server, sslConnectionFactories);
        connector.setAcceptQueueSize(acceptQueueSize);
        connector.setPort(port);
        connector.setIdleTimeout(idleTimeout);
        if (listenHostName != null) {
            connector.setHost(listenHostName);
        }

        /** Each ServerConnector should have a unique name declared.
         * When defining the ServletContextHandler, we will use virtual hosts with this name, syntax is: "@{name}",
         * see: {@link UmsJettyContainer#buildHealthCheckContextHandler()} */
        connector.setName(port == statusPort ? STATUS_CONNECTOR : HTTPS_CONNECTOR);
        server.addConnector(connector);
    }

    /** Create a SslContextFactory using configurtioan */
    private SslContextFactory createSSLContextFactory(final boolean needClientAuth) throws Exception {

        final SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setEndpointIdentificationAlgorithm(null);

        if (needClientAuth) {
            sslContextFactory.setNeedClientAuth(true);   // Force mTLS (for "real" requests)
        } else {
            sslContextFactory.setWantClientAuth(true);   // Don't force mTLS (for health-checks)
        }
        if (jettyProperties.getKeyStorePath() != null && jettyProperties.getKeyStorePassword() != null && jettyProperties.getKeyStoreType() != null) {
            sslContextFactory.setKeyStorePath(jettyProperties.getKeyStorePath());
           // sslContextFactory.setKeyStorePassword(keyStore.getSecret(jettyProperties.getKeyStorePassword()));
            sslContextFactory.setKeyStoreType(jettyProperties.getKeyStoreType());
        } else {
           // sslContextFactory.setSslContext(buildRefreshableSslContext());
        }

        if (jettyProperties.getSslTrustStorePath() != null && jettyProperties.getSslTrustStorePasswordKeyname() != null && jettyProperties.getSslTrustStoreType() != null) {
            sslContextFactory.setTrustStorePath(jettyProperties.getSslTrustStorePath());
            //sslContextFactory.setTrustStorePassword(keyStore.getSecret(jettyProperties.getSslTrustStorePasswordKeyname()));
            sslContextFactory.setTrustStoreType(jettyProperties.getSslTrustStoreType());
        }

        final String keyManagerPassword = jettyProperties.getSslKeyManagerPasswordKeyname();
        if (keyManagerPassword != null) {
            //sslContextFactory.setKeyManagerPassword(keyStore.getSecret(keyManagerPassword));
        }

        final String includedCipherSuites = jettyProperties.getSslIncludedCipherSuites();
        if (includedCipherSuites != null && !includedCipherSuites.isEmpty()) {
            sslContextFactory.setIncludeCipherSuites(includedCipherSuites.split(","));
        }

        final String excludedCipherSuites = jettyProperties.getSslExcludedCipherSuites();
        if (excludedCipherSuites != null && !excludedCipherSuites.isEmpty()) {
            sslContextFactory.setExcludeCipherSuites(excludedCipherSuites.split(","));
        }

        final String excludedProtocols = jettyProperties.getSslExcludedProtocols();
        if (!excludedProtocols.isEmpty()) {
            sslContextFactory.setExcludeProtocols(excludedProtocols.split(","));
        }

        sslContextFactory.setEnableOCSP(jettyProperties.getSslEnableOcsp());

        sslContextFactory.setRenegotiationAllowed(jettyProperties.getSslRenegotiationAllowed());

        return sslContextFactory;
    }
}
