package com.hzokbe.quarkus.start.controller.user;

import com.hzokbe.quarkus.start.dto.user.create.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }
}
