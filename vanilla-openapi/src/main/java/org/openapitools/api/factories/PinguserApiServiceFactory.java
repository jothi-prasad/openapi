package org.openapitools.api.factories;

import org.openapitools.api.PinguserApiService;
import org.openapitools.api.impl.PinguserApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T23:35:27.298-07:00[America/Los_Angeles]")
public class PinguserApiServiceFactory {
    private final static PinguserApiService service = new PinguserApiServiceImpl();

    public static PinguserApiService getPinguserApi() {
        return service;
    }
}
