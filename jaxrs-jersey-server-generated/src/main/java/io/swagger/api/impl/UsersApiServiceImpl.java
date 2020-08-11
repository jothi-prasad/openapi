package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.api.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-08-06T23:24:24.936Z[GMT]")
public class UsersApiServiceImpl extends UsersApiService {
    List<User> userList = new ArrayList<User>();
    @Override
    public Response create(SecurityContext securityContext) throws NotFoundException {
        // do some magic!

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "added user successfully")).build();
    }
    @Override
    public Response listUsers(Integer limit, SecurityContext securityContext) throws ApiException {
        // do some magic!
        System.out.println("sec acheme.." + securityContext.getAuthenticationScheme());
        System.out.println("client cert sec.." + securityContext.CLIENT_CERT_AUTH);
        System.out.println("getruserprincipal.." + securityContext.getUserPrincipal());
        System.out.println("issecure.." + securityContext.isSecure());
        System.out.println("user.." + securityContext.isUserInRole("user"));

        User usr = new User();
        if (limit != 0 ) {
            Long id = Long.valueOf(123);
            usr.setUserName("jothi");
            usr.setUserId(id);
            usr.setUserDescription("first user");
            usr.setSystem("YANIS2");
        } else {
            throw new ApiException(400, "failed get user");
        }
        return Response.ok().entity(usr).build();//new ApiResponseMessage(ApiResponseMessage.WARNING, "you cannot get the full user list!")).build();
        //return usr;
    //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, usr)).build();
    }
}
