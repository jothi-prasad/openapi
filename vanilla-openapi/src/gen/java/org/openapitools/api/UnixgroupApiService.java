package org.openapitools.api;

import org.openapitools.api.*;
import org.openapitools.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import org.openapitools.model.Group;
import org.openapitools.model.GroupStub;

import java.util.List;
import org.openapitools.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2020-08-13T15:39:06.082-07:00[America/Los_Angeles]")
public abstract class UnixgroupApiService {
    public abstract Response unixgroupGet(SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixgroupNameGet(String name,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixgroupNameMemberMembernameDelete(String name,String membername,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixgroupNameMemberMembernamePut(String name,String membername,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixgroupNamePut(String name,GroupStub groupStub,SecurityContext securityContext) throws NotFoundException;
    public abstract Response unixgroupPost(GroupStub groupStub,SecurityContext securityContext) throws NotFoundException;
}
