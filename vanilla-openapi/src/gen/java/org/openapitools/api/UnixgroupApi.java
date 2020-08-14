package org.openapitools.api;

import org.openapitools.model.*;
import org.openapitools.api.UnixgroupApiService;
import org.openapitools.api.factories.UnixgroupApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import org.openapitools.model.Group;
import org.openapitools.model.GroupStub;

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

@Path("/unixgroup")


@io.swagger.annotations.Api(description = "the unixgroup API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public class UnixgroupApi  {
   private final UnixgroupApiService delegate;

   public UnixgroupApi(@Context ServletConfig servletContext) {
      UnixgroupApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UnixgroupApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UnixgroupApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UnixgroupApiServiceFactory.getUnixgroupApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "list of all unix groups", response = Group.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "list of all unix groups", response = Group.class, responseContainer = "List") })
    public Response unixgroupGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupGet(securityContext);
    }
    @GET
    @Path("/{name}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Group.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "unixgroup details for the name provided", response = Group.class) })
    public Response unixgroupNameGet(@ApiParam(value = "unixgroup name", required = true) @PathParam("name") @NotNull  String name
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupNameGet(name, securityContext);
    }
    @DELETE
    @Path("/{name}/member/{membername}")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "disable a unix headless user", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No Content", response = Void.class) })
    public Response unixgroupNameMemberMembernameDelete(@ApiParam(value = "unixgroup name", required = true) @PathParam("name") @NotNull  String name
,@ApiParam(value = "membername", required = true) @PathParam("membername") @NotNull  String membername
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupNameMemberMembernameDelete(name, membername, securityContext);
    }
    @PUT
    @Path("/{name}/member/{membername}")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "disable/enable a unix headless user", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No Content", response = Void.class) })
    public Response unixgroupNameMemberMembernamePut(@ApiParam(value = "unixgroup name", required = true) @PathParam("name") @NotNull  String name
,@ApiParam(value = "membername", required = true) @PathParam("membername") @NotNull  String membername
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupNameMemberMembernamePut(name, membername, securityContext);
    }
    @PUT
    @Path("/{name}")
    @Consumes({ "application/json" })
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No Content", response = Void.class) })
    public Response unixgroupNamePut(@ApiParam(value = "unixgroup name", required = true) @PathParam("name") @NotNull  String name
,@ApiParam(value = "") @Valid  GroupStub groupStub
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupNamePut(name, groupStub, securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "create a unix group", response = Group.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "unixgroup created", response = Group.class) })
    public Response unixgroupPost(@ApiParam(value = "") @Valid  GroupStub groupStub
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.unixgroupPost(groupStub, securityContext);
    }
}
