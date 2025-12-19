package com.hzokbe.quarkus.start.controller.auth;

import com.hzokbe.quarkus.start.dto.user.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {
    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @POST
    @Path("/sign-up")
    @Transactional
    public Response signUp(CreateUserRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }
}
