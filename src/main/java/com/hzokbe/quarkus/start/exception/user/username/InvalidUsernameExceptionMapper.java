package com.hzokbe.quarkus.start.exception.user.username;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidUsernameExceptionMapper implements ExceptionMapper<InvalidUsernameException> {
    @Override
    public Response toResponse(InvalidUsernameException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
