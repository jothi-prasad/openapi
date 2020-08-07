package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.api.NotFoundException;

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
    public User listUsers(Integer limit, SecurityContext securityContext) throws ApiException {
        // do some magic!
        User usr = new User();
        if (limit != 0 ) {
            Long id = Long.valueOf(123);
            usr.setUserName("jothi");
            usr.setUserId(id);
            usr.setUserDescription("first user");
            usr.setSystem("YANIS");
        } else {
            throw new ApiException(400, "failed get user");
        }
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.WARNING, "you cannot get the full user list!")).build();
        return usr;
    }
}
