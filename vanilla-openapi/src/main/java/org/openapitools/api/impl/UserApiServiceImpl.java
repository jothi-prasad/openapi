package org.openapitools.api.impl;

import org.openapitools.api.*;
import org.openapitools.model.*;

import org.openapitools.model.User;
import org.openapitools.model.UserList;

import java.util.ArrayList;
import java.util.List;
import org.openapitools.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T18:01:08.263-07:00[America/Los_Angeles]")
public class UserApiServiceImpl extends UserApiService{


    Authorize auth = new Authorize();
    DBService dbService = new DBService();
//    @Context
//    private HttpServletRequest httpServletRequest;
//    @Context
//    private HttpServletResponse httpServletResponse;

    @Override
    public Response userGet(String xRequestID, String type, SecurityContext securityContext) throws NotFoundException {

        if (type.equalsIgnoreCase("headless") && auth.evaluateRequest(xRequestID)) {
            return Response.ok().entity(dbService.getDBUserlist(type, xRequestID)).build();
        }
        else {
            return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "please enter the supported UserType")).build();
        }
    }
    @Override
    public Response userNameGet(String name, @NotNull String xRequestID, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
