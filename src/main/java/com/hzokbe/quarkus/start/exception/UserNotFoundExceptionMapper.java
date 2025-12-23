package com.hzokbe.quarkus.start.exception;

import com.hzokbe.quarkus.start.dto.exception.response.ExceptionResponseDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponseDTO(exception.getMessage()))
                .build();
    }
}
