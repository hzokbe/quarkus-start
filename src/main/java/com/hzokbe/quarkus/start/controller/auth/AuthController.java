package com.hzokbe.quarkus.start.controller.auth;

import com.hzokbe.quarkus.start.dto.auth.sign.in.SignInRequestDTO;
import com.hzokbe.quarkus.start.dto.user.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.service.auth.AuthService;
import com.hzokbe.quarkus.start.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {
    private final UserService userService;

    private final AuthService service;

    public AuthController(UserService userService, AuthService service) {
        this.userService = userService;

        this.service = service;
    }

    @POST
    @Path("/sign-up")
    @Transactional
    public Response signUp(CreateUserRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(userService.create(dto)).build();
    }

    @POST
    @Path("/sign-in")
    @Transactional
    public Response signIn(SignInRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.signIn(dto)).build();
    }
}
