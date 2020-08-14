package org.openapitools.api;

import org.openapitools.model.*;
import org.openapitools.api.UnixheadlessApiService;
import org.openapitools.api.factories.UnixheadlessApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.openapitools.model.HeadlessUserStub;
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

@Path("/unixheadless")


@io.swagger.annotations.Api(description = "the unixheadless API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public class UnixheadlessApi  {
   private final UnixheadlessApiService delegate;

   public UnixheadlessApi(@Context ServletConfig servletContext) {
      UnixheadlessApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UnixheadlessApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UnixheadlessApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UnixheadlessApiServiceFactory.getUnixheadlessApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/{name}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "headless details for the name provided", response = User.class) })
    public Response unixheadlessNameGet(@ApiParam(value = "headless user login name", required = true) @PathParam("name") @NotNull  String name
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixheadlessNameGet(name, securityContext);
    }
    @PUT
    @Path("/{name}")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "TODO - address questions about renaming", notes = "disable/enable a unix headless user", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No Content", response = Void.class) })
    public Response unixheadlessNamePut(@ApiParam(value = "headless user login name", required = true) @PathParam("name") @NotNull  String name
,@ApiParam(value = "") @Valid  HeadlessUserStub headlessUserStub
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixheadlessNamePut(name, headlessUserStub, securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "create a unix headless user", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "unix headless user created", response = User.class) })
    public Response unixheadlessPost(@ApiParam(value = "") @Valid  HeadlessUserStub headlessUserStub
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixheadlessPost(headlessUserStub, securityContext);
    }
}
