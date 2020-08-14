package org.openapitools.api.factories;

import org.openapitools.api.PingApiService;
import org.openapitools.api.PinguserApi;
//import org.openapitools.api.impl.PingApiServiceImpl;
import org.openapitools.api.PinguserApiService;
import org.openapitools.api.impl.PinguserApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T17:55:44.874-07:00[America/Los_Angeles]")
public class PingApiServiceFactory {
    private final static PinguserApiService service = new PinguserApiServiceImpl();

    public static PinguserApiService getPingApi() {
        return service;
    }
}
