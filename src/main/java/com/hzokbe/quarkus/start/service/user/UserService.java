package com.hzokbe.quarkus.start.service.user;

import com.hzokbe.quarkus.start.dto.user.response.CreateUserResponseDTO;
import com.hzokbe.quarkus.start.dto.user.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.exception.user.AlreadyRegisteredUserException;
import com.hzokbe.quarkus.start.model.user.User;
import com.hzokbe.quarkus.start.service.password.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    private final PasswordService passwordService;

    public UserService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public CreateUserResponseDTO create(CreateUserRequestDTO dto) {
        if (User.count("username", dto.getUsername()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        if (User.count("email", dto.getEmail()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var user = new User();

        user.username = dto.getUsername();

        user.email = dto.getEmail();

        user.password = passwordService.hash(dto.getPassword());

        User.persist(user);

        return new CreateUserResponseDTO(
                user.id,
                user.username,
                user.email
        );
    }
}
