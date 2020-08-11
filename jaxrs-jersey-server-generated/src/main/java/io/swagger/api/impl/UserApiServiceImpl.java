package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Error;
import io.swagger.model.Users;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-08-06T23:24:24.936Z[GMT]")
public class UserApiServiceImpl extends UserApiService {
    @Override
    public Response getUser(Integer userId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        User usr = new User();
        usr.setUserId(userId.longValue());
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
        //return usr;
        return Response.ok().entity(usr).build();
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, usr)).build();
    }
}
