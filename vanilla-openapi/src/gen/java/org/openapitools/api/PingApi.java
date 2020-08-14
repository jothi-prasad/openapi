package org.openapitools.api;

import org.openapitools.api.factories.PinguserApiServiceFactory;
import org.openapitools.model.*;
import org.openapitools.api.PingApiService;
import org.openapitools.api.factories.PingApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;


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

@Path("/ping")


@io.swagger.annotations.Api(description = "the ping API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-12T17:55:44.874-07:00[America/Los_Angeles]")
public class PingApi  {
   private final PinguserApiService delegate;

   public PingApi(@Context ServletConfig servletContext) {
       PinguserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("PingApi.implementation");
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
    
    
    
    @io.swagger.annotations.ApiOperation(value = "Checks if the server is alive", notes = "", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "No Content", response = Void.class) })
    public Response pingGet(@ApiParam(value = "" ,required=true)@HeaderParam("X-Request-ID") String xRequestID
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return null;// delegate.pinguserUsernameGet(user, xRequestID, securityContext);
    }
}
