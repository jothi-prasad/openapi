package org.openapitools.api;

import org.openapitools.api.*;
import org.openapitools.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import org.openapitools.model.HeadlessUserStub;
import org.openapitools.model.User;

import java.util.List;
import org.openapitools.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public abstract class UnixheadlessApiService {
    public abstract Response unixheadlessNameGet(String name,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixheadlessNamePut(String name,HeadlessUserStub headlessUserStub,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixheadlessPost(HeadlessUserStub headlessUserStub,SecurityContext securityContext) throws NotFoundException;
}
