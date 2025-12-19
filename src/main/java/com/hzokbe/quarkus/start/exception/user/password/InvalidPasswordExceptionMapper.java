package com.hzokbe.quarkus.start.exception.user.password;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidPasswordExceptionMapper implements ExceptionMapper<InvalidPasswordException> {
    @Override
    public Response toResponse(InvalidPasswordException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
