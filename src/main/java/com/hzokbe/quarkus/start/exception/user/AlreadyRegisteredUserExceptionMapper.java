package com.hzokbe.quarkus.start.exception.user;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AlreadyRegisteredUserExceptionMapper implements ExceptionMapper<AlreadyRegisteredUserException> {
    @Override
    public Response toResponse(AlreadyRegisteredUserException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
