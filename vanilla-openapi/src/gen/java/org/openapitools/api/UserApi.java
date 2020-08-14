package org.openapitools.api;

import org.openapitools.model.*;
import org.openapitools.api.UserApiService;
import org.openapitools.api.factories.UserApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.openapitools.model.User;
import org.openapitools.model.UserList;

import java.util.Map;
import java.util.List;
import org.openapitools.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/user")


@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public class UserApi  {
   private final UserApiService delegate;

   public UserApi(@Context ServletConfig servletContext) {
      UserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UserApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UserApiServiceFactory.getUserApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = UserList.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "list of all users", response = UserList.class, responseContainer = "List") })
    public Response userGet(@ApiParam(value = "" ,required=true)@HeaderParam("X-Request-ID") String xRequestID
,@ApiParam(value = "flag include headless only", allowableValues="headless, human, all", defaultValue = "headless") @DefaultValue("headless") @QueryParam("type")  String type
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userGet(xRequestID, type, securityContext);
    }
    @GET
    @Path("/{name}")
    
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "user details for the name provided", response = User.class) })
    public Response userNameGet(@ApiParam(value = "user's login name", required = true) @PathParam("name") @NotNull  String name
,@ApiParam(value = "" ,required=true)@HeaderParam("X-Request-ID") String xRequestID
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userNameGet(name, xRequestID, securityContext);
    }
}
