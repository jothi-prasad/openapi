package org.openapitools.api.factories;

import org.openapitools.api.UnixgroupApiService;
import org.openapitools.api.impl.UnixgroupApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public class UnixgroupApiServiceFactory {
    private final static UnixgroupApiService service = new UnixgroupApiServiceImpl();

    public static UnixgroupApiService getUnixgroupApi() {
        return service;
    }
}
