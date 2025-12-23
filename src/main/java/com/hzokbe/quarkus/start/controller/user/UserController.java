package com.hzokbe.quarkus.start.controller.user;

import com.hzokbe.quarkus.start.dto.user.create.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.dto.user.update.request.UpdateUserRequestDTO;
import com.hzokbe.quarkus.start.service.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @POST
    @RolesAllowed({ "admin" })
    public Response createUser(CreateUserRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.create(dto)).build();
    }

    @GET
    @RolesAllowed({ "admin" })
    public Response getAllUsers() {
        return Response.status(Response.Status.OK).entity(service.getAllUsers()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "admin" })
    public Response getUserById(@PathParam("id") UUID id) {
        return Response.status(Response.Status.OK).entity(service.getUserById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "admin" })
    public Response updateUser(@PathParam("id") UUID id, UpdateUserRequestDTO dto) {
        return Response.status(Response.Status.OK).entity(service.updateUser(id, dto)).build();
    }
}
