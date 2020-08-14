package org.openapitools.api;

import org.openapitools.model.*;
import org.openapitools.api.PinguserApiService;
import org.openapitools.api.factories.PinguserApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.openapitools.model.User;

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

@Path("/pinguser")


@io.swagger.annotations.Api(description = "the pinguser API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T23:35:27.298-07:00[America/Los_Angeles]")
public class PinguserApi  {
   private final PinguserApiService delegate;

   public PinguserApi(@Context ServletConfig servletContext) {
      PinguserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("PinguserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (PinguserApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = PinguserApiServiceFactory.getPinguserApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/{username}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Checks if the server is alive", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "user details for the name provided", response = User.class) })
    public Response pinguserUsernameGet(@ApiParam(value = "user name", required = true) @PathParam("username") @NotNull  String username
,@ApiParam(value = "" ,required=true)@HeaderParam("X-Request-ID") String xRequestID
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.pinguserUsernameGet(username, xRequestID, securityContext);
    }
}
