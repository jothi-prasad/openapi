package org.openapitools.api.impl;

import org.openapitools.api.*;
import org.openapitools.model.*;

import org.openapitools.model.User;

import java.util.List;
import org.openapitools.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T18:15:36.854-07:00[America/Los_Angeles]")
public class PinguserApiServiceImpl extends PinguserApiService {
    @Override
    public Response pinguserUsernameGet(String username,  @NotNull String xRequestID, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println("header param..." + xRequestID);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, xRequestID)).build();
    }
}
