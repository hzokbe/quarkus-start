package com.hzokbe.quarkus.start.exception.user.email;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidEmailExceptionMapper implements ExceptionMapper<InvalidEmailException> {
    @Override
    public Response toResponse(InvalidEmailException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
