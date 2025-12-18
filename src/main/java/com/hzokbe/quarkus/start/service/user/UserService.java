package com.hzokbe.quarkus.start.service.user;

import com.hzokbe.quarkus.start.dto.user.CreateUserResponseDTO;
import com.hzokbe.quarkus.start.dto.user.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.model.user.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    public CreateUserResponseDTO create(CreateUserRequestDTO dto) {
        var user = new User();

        user.username = dto.getUsername();

        user.email = dto.getEmail();

        User.persist(user);

        return new CreateUserResponseDTO(
                user.id,
                user.username,
                user.email
        );
    }
}
