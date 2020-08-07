package io.swagger.api;

import io.swagger.model.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-08-06T23:24:24.936Z[GMT]")public abstract class UsersApiService {
    public abstract Response create(SecurityContext securityContext) throws NotFoundException;
    public abstract User listUsers(Integer limit, SecurityContext securityContext) throws ApiException;
}
