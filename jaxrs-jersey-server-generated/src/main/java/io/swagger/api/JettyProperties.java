package io.swagger.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class JettyProperties {

    /**
     * The max threads count to handle requests.
     * @see org.eclipse.jetty.util.thread.QueuedThreadPool#setMaxThreads(int)
     */
    private static final Integer MAX_REQUEST_HANDLING_THREADS_DEFAULT = 1024;
    private Integer httpMaxThreads;

    /**
     * The maximum Idle time for a connection (milliseconds).
     * @see org.eclipse.jetty.server.AbstractConnector#setIdleTimeout(long)
     */
    private static final Integer IDLE_TIMEOUT_DEFAULT = 30000;
    private Integer httpIdleTimeout;

    /**
     * The path of jetty's home-folder
     * This only serves as the base-path for the path-configurations below, so if all of them are specified - then this is not used.
     */
    private static final String HOME_PATH_DEFAULT = "/opt/ums";
    private String homePath;

    /**
     * The port that Jetty will listen on for HTTPS connection (0=disabled)
     */
    private static final Integer HTTPS_PORT_DEFAULT = 4443;
    private Integer httpsPort;

    /** The port that Jetty will listen on for HTTP connection (0=disabled) - disabled by default */
    private static final Integer HTTP_PORT_DEFAULT = 0;
    private Integer httpPort;

    /** The default for the status-port is the https-port, or if not set - the https-port, or if not set - the http-port (0=disabled) */
    private static final Integer STATUS_PORT_DEFAULT = 0;
    private Integer statusPort;

    /** Athenz-provided certificate file */
    private String certificatePath;

    /** Athenz-provided private-key file */
    private String privateKeyPath;

    /** The application-secret name to get the value for
     * {@link org.eclipse.jetty.util.ssl.SslContextFactory#setKeyManagerPassword(java.lang.String)}
     * (this is probably an aws-s3 key) */
    private String sslKeyManagerPasswordKeyname;

    /**
     * The path to the trust-store file that contains CA certificates trusted by Jetty
     * @see org.eclipse.jetty.util.ssl.SslContextFactory#setTrustStorePath(java.lang.String)
     */
    private String sslTrustStorePath;

    /** The application-secret name to get the password for the trust-store file (this is probably an aws-s3 key). */
    private String sslTrustStorePasswordKeyname;

    /** Specifies the type for the truststore */
    private String sslTrustStoreType;

    /**
     * List of cipher suites to exclude from TLS negotiation.
     * @see org.eclipse.jetty.util.ssl.SslContextFactory#setExcludeCipherSuites(java.lang.String...)
     */
    private String sslExcludedCipherSuites;

    /** @see org.eclipse.jetty.util.ssl.SslContextFactory#setIncludeCipherSuites(java.lang.String...) */
    private String sslIncludedCipherSuites;

    /**
     * A comma-separated list of HTTP methods to disable (e.g. response with 405 "Method Not Allowed"
     */
    private static final String DISABLE_HTTP_METHODS_CSV_DEFAULT = "OPTIONS,TRACE";
    private String disableHttpMethodsCsv;

    /**
     * If set to "true" - then {@link org.eclipse.jetty.server.DebugListener} is added to jetty.
     */
    private static final boolean DEBUG_DEFAULT = false;
    private Boolean debug;

    /** If set to "true" (which is the default) - all requests will respond the HTTP header "Connection: close" - so connections are not reused. */
    private static final boolean KEEP_ALIVE_DEFAULT = true;
    private Boolean keepAlive;

    /**
     * The accept-queue-size (a.k.a. accept-backlog): the maximum length to which the queue of pending connections may grow.
     * If a request arrives when all threads are occupied and the queue is full, the request fails.
     * A value of 0 will use the Jetty's default (which seems to be 50).
     * See https://linux.die.net/man/2/listen
     * @see org.eclipse.jetty.server.ServerConnector#setAcceptQueueSize(int)
     */
    private static final int ACCEPT_QUEUE_SIZE_DEFAULT = 0;
    private Integer acceptQueueSize;

    /**
     * The path of the "webapps" folder
     * @see org.eclipse.jetty.deploy.providers.ScanningAppProvider#setMonitoredDirName(java.lang.String)
     */
    private static final String WEBAPPS_FOLDER_RELATIVE_DEFAULT = "/webapps";
    private String webappsFolder;

    /**
     * The path of jetty's temp-folder
     * @see org.eclipse.jetty.deploy.providers.WebAppProvider#setTempDir(java.io.File)
     */
    private static final String TEMP_FOLDER_RELATIVE_DEFAULT = "/temp";
    private String tempFolder;

    /** @see org.eclipse.jetty.server.HttpConfiguration#setRequestHeaderSize(int) */
    private static final Integer REQUEST_HEADER_SIZE_DEFAULT = 8192;
    private Integer httpRequestHeaderSize;

    /** @see org.eclipse.jetty.server.HttpConfiguration#setSendDateHeader(boolean) */
    private static final Boolean SEND_DATE_HEADER_DEFAULT = false;
    private Boolean httpSendDateHeader;

    /** @see org.eclipse.jetty.server.HttpConfiguration#setSendServerVersion(boolean) */
    private static final Boolean SEND_SERVER_VERSION_DEFAULT = false;
    private Boolean httpSendServerVersion;

    /** @see org.eclipse.jetty.server.HttpConfiguration#setResponseHeaderSize(int) */
    private static final Integer    RESPONSE_HEADER_SIZE_DEFAULT = 8192;
    private Integer httpResponseHeaderSize;

    /** @see org.eclipse.jetty.server.HttpConfiguration#setOutputBufferSize(int) */
    private static final Integer    OUTPUT_BUFFER_SIZE_DEFAULT = 32768;
    private Integer httpOutputBufferSize;

    /**
     * The path of the default "web.xml" file
     * This file is applied to all web-applications before their own "WEB_INF/web.xml"
     * @see org.eclipse.jetty.deploy.providers.WebAppProvider#setDefaultsDescriptor(java.lang.String)
     */
    private static final String WEBDEFAULT_XML_FILE_RELATIVE_DEFAULT = "/conf/webdefault.xml";
    private String webdefaultXmlFile;

    /** @see org.eclipse.jetty.util.ssl.SslContextFactory#setExcludeProtocols(java.lang.String...) */
    private static final String  EXCLUDED_PROTOCOLS_DEFAULT = "SSLv2,SSLv3";
    private String sslExcludedProtocols;

    /** @see org.eclipse.jetty.util.ssl.SslContextFactory#setEnableOCSP(boolean) */
    private static final boolean ENABLE_OCSP_DEFAULT = false;
    private Boolean sslEnableOcsp;

    /** @see org.eclipse.jetty.util.ssl.SslContextFactory#setRenegotiationAllowed(boolean) */
    private static final boolean RENEGOTIATION_ALLOWED_DEFAULT = true;
    private Boolean sslRenegotiationAllowed;

    /** comma separated list of health check uri's */
    private static final String HEALTH_CHECK_DEFAULT = "/status.html";
    private String healthCheckUriList;

    /**
     * The base-dir for the health-check files
     */
    private static final String HEALTH_CHECK_PATH_DEFAULT = "/opt/ums/health_check";
    private String healthCheckPath;

    /** Password for the keystore */
    private String keyStorePassword;

    /** The path to the keystore file that contains the server's certificate */
    private String keyStorePath;

    /** Specifies the type for the keystore */
    private String keyStoreType;

    /**
     * For HTTP access specifies the IP address/Host for service to listen on.
     * This could be necessary, for example, if the system administrator
     * wants some proxy server (e.g. ATS) to handle TLS traffic and configure
     * Jetty to listen on 127.0.0.1 loopback address only for HTTP connections from that proxy server
     * */
    private String listenHost;

    private final String envPwd;

    public JettyProperties() {
        envPwd = System.getenv("PWD");
    }

    public static JettyProperties loadJettyProps(final InputStream propFile) throws IOException {
        final JavaPropsMapper mapper = new JavaPropsMapper();
        return mapper.readerFor(JettyProperties.class)
                .with(JavaPropsSchema.emptySchema()
                        .withPrefix("ums.jetty")) // every property in this file should start with this prefix
                .readValue(propFile);
    }

    private <T> T defaultIfNotPresent(final T currentVal, final T defaultValue) {
        return defaultIfNotPresent(currentVal, defaultValue, false);
    }

    private <T> T defaultIfNotPresent(final T currentVal, final T defaultValue, final boolean resolveMarkers) {
        T retVal = currentVal != null ? currentVal : defaultValue;
        if (resolveMarkers && envPwd != null && retVal instanceof String) {
            retVal = (T) ((String) retVal).replace("${PWD}", System.getenv("PWD"));
        }
        return retVal;
    }

    public int getHttpMaxThreads() {
        return defaultIfNotPresent(httpMaxThreads, MAX_REQUEST_HANDLING_THREADS_DEFAULT);
    }

    public int getHttpIdleTimeout() {
        return defaultIfNotPresent(httpIdleTimeout, IDLE_TIMEOUT_DEFAULT);
    }

    public String getHomePath() {
        return defaultIfNotPresent(homePath, HOME_PATH_DEFAULT);
    }

    public int getHttpsPort() {
        return defaultIfNotPresent(httpsPort, HTTPS_PORT_DEFAULT);
    }

    public int getStatusPort() {
        return defaultIfNotPresent(statusPort, STATUS_PORT_DEFAULT);
    }

    public int getHttpRequestHeaderSize() {
        return defaultIfNotPresent(httpRequestHeaderSize, REQUEST_HEADER_SIZE_DEFAULT);
    }

    public boolean getHttpSendDateHeader() {
        return defaultIfNotPresent(httpSendDateHeader, SEND_DATE_HEADER_DEFAULT);
    }

    public String getCertificatePath() {
        return defaultIfNotPresent(certificatePath, null, true);
    }

    public String getPrivateKeyPath() {
        return defaultIfNotPresent(privateKeyPath, null, true);
    }

    public String getSslTrustStorePath() {
        return defaultIfNotPresent(sslTrustStorePath, null, true);
    }

    public String getSslTrustStorePasswordKeyname() {
        return defaultIfNotPresent(sslTrustStorePasswordKeyname, null, true);
    }

    public String getSslExcludedCipherSuites() {
        return sslExcludedCipherSuites;
    }

    public String getSslIncludedCipherSuites() {
        return sslIncludedCipherSuites;
    }

    public List<String> getDisableHttpMethodsCsv() {
        return Stream.of(defaultIfNotPresent(disableHttpMethodsCsv, DISABLE_HTTP_METHODS_CSV_DEFAULT)
                .split(","))
                .map(String::trim)
                .filter(method -> ! method.isEmpty())
                .collect(Collectors.toList());
    }

    public Boolean getKeepAlive() {
        return defaultIfNotPresent(keepAlive, KEEP_ALIVE_DEFAULT);
    }

    public String getWebappsFolder() {
        return defaultIfNotPresent(webappsFolder, getHomePath() + WEBAPPS_FOLDER_RELATIVE_DEFAULT, true);
    }

    public String getTempFolder() {
        return defaultIfNotPresent(tempFolder, getHomePath() + TEMP_FOLDER_RELATIVE_DEFAULT, true);
    }

    public Integer getAcceptQueueSize() {
        return defaultIfNotPresent(acceptQueueSize, ACCEPT_QUEUE_SIZE_DEFAULT);
    }

    public Boolean getHttpSendServerVersion() {
        return defaultIfNotPresent(httpSendServerVersion, SEND_SERVER_VERSION_DEFAULT);
    }

    public Integer getHttpResponseHeaderSize() {
        return defaultIfNotPresent(httpResponseHeaderSize, RESPONSE_HEADER_SIZE_DEFAULT);
    }

    public Integer getHttpOutputBufferSize() {
        return defaultIfNotPresent(httpOutputBufferSize, OUTPUT_BUFFER_SIZE_DEFAULT);
    }

    public String getWebdefaultXmlFile() {
        return defaultIfNotPresent(webdefaultXmlFile, getHomePath() + WEBDEFAULT_XML_FILE_RELATIVE_DEFAULT, true);
    }

    public String getSslKeyManagerPasswordKeyname() {
        return sslKeyManagerPasswordKeyname;
    }

    public String getSslExcludedProtocols() {
        return defaultIfNotPresent(sslExcludedProtocols, EXCLUDED_PROTOCOLS_DEFAULT);
    }

    public Boolean getSslEnableOcsp() {
        return defaultIfNotPresent(sslEnableOcsp, ENABLE_OCSP_DEFAULT);
    }

    public Boolean getSslRenegotiationAllowed() {
        return defaultIfNotPresent(sslRenegotiationAllowed, RENEGOTIATION_ALLOWED_DEFAULT);
    }

    public Integer getHttpPort() {
        return defaultIfNotPresent(httpPort, HTTP_PORT_DEFAULT);
    }

    public Boolean getDebug() {
        return defaultIfNotPresent(debug, DEBUG_DEFAULT);
    }

    public String getHealthCheckUriList() {
        return defaultIfNotPresent(healthCheckUriList, HEALTH_CHECK_DEFAULT);
    }

    public String getHealthCheckPath() {
        return defaultIfNotPresent(healthCheckPath, HEALTH_CHECK_PATH_DEFAULT);
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }
    public String getKeyStorePath() {
        return keyStorePath;
    }

    public String getSslTrustStoreType() {
        return sslTrustStoreType;
    }

    public String getListenHost() {
        return listenHost;
    }

    public void setListenHost(final String listenHost) {
        this.listenHost = listenHost;
    }

    public void setKeyStorePassword(final String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }
    public void setKeyStorePath(final String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    public void setKeyStoreType(final String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public void setSslTrustStoreType(final String sslTrustStoreType) {
        this.sslTrustStoreType = sslTrustStoreType;
    }

    public void setHealthCheckPath(final String healthCheckPath) {
        this.healthCheckPath = healthCheckPath;
    }

    public void setHealthCheckUriList(final String healthCheckUriList) {
        this.healthCheckUriList = healthCheckUriList;
    }

    public void setDisableHttpMethodsCsv(final String disableHttpMethodsCsv) {
        this.disableHttpMethodsCsv = disableHttpMethodsCsv;
    }

    public void setHttpMaxThreads(final Integer httpMaxThreads) {
        this.httpMaxThreads = httpMaxThreads;
    }

    public void setHttpIdleTimeout(final Integer httpIdleTimeout) {
        this.httpIdleTimeout = httpIdleTimeout;
    }

    public void setHttpsPort(final Integer httpsPort) {
        this.httpsPort = httpsPort;
    }

    public void setStatusPort(final Integer statusPort) {
        this.statusPort = statusPort;
    }

    public void setDebug(final Boolean debug) {
        this.debug = debug;
    }

    public void setKeepAlive(final Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public void setAcceptQueueSize(final Integer acceptQueueSize) {
        this.acceptQueueSize = acceptQueueSize;
    }

    public void setWebappsFolder(final String webappsFolder) {
        this.webappsFolder = webappsFolder;
    }

    public void setTempFolder(final String tempFolder) {
        this.tempFolder = tempFolder;
    }

    public void setHttpRequestHeaderSize(final Integer httpRequestHeaderSize) {
        this.httpRequestHeaderSize = httpRequestHeaderSize;
    }

    public void setHttpSendDateHeader(final Boolean httpSendDateHeader) {
        this.httpSendDateHeader = httpSendDateHeader;
    }

    public void setHttpSendServerVersion(final Boolean httpSendServerVersion) {
        this.httpSendServerVersion = httpSendServerVersion;
    }

    public void setHttpResponseHeaderSize(final Integer httpResponseHeaderSize) {
        this.httpResponseHeaderSize = httpResponseHeaderSize;
    }

    public void setHttpOutputBufferSize(final Integer httpOutputBufferSize) {
        this.httpOutputBufferSize = httpOutputBufferSize;
    }

    public void setWebdefaultXmlFile(final String webdefaultXmlFile) {
        this.webdefaultXmlFile = webdefaultXmlFile;
    }

    public void setSslKeyManagerPasswordKeyname(final String sslKeyManagerPasswordKeyname) {
        this.sslKeyManagerPasswordKeyname = sslKeyManagerPasswordKeyname;
    }

    public void setSslExcludedProtocols(final String sslExcludedProtocols) {
        this.sslExcludedProtocols = sslExcludedProtocols;
    }

    public void setSslEnableOcsp(final Boolean sslEnableOcsp) {
        this.sslEnableOcsp = sslEnableOcsp;
    }

    public void setSslRenegotiationAllowed(final Boolean sslRenegotiationAllowed) {
        this.sslRenegotiationAllowed = sslRenegotiationAllowed;
    }

    public void setHttpPort(final Integer httpPort) {
        this.httpPort = httpPort;
    }

    public void setHomePath(final String homePath) {
        this.homePath = homePath;
    }

    public void setCertificatePath(final String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public void setPrivateKeyPath(final String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public void setSslTrustStorePath(final String sslTrustStorePath) {
        this.sslTrustStorePath = sslTrustStorePath;
    }

    public void setSslTrustStorePasswordKeyname(final String sslTrustStorePasswordKeyname) {
        this.sslTrustStorePasswordKeyname = sslTrustStorePasswordKeyname;
    }

    public void setSslExcludedCipherSuites(final String sslExcludedCipherSuites) {
        this.sslExcludedCipherSuites = sslExcludedCipherSuites;
    }

    public void setSslIncludedCipherSuites(final String sslIncludedCipherSuites) {
        this.sslIncludedCipherSuites = sslIncludedCipherSuites;
    }

}
