package org.openapitools.api.impl;
import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@ApplicationPath("/api")
public class MyApplication extends Application {
    public MyApplication(@Context ServletConfig servletConfig) {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.3");
        beanConfig.setTitle("vanilla API");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.openapitools");
        beanConfig.setScan(true);
    }
}
