package com.hzokbe.quarkus.start.service.user;

import com.hzokbe.quarkus.start.dto.user.response.CreateUserResponseDTO;
import com.hzokbe.quarkus.start.dto.user.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.exception.user.AlreadyRegisteredUserException;
import com.hzokbe.quarkus.start.exception.user.email.InvalidEmailException;
import com.hzokbe.quarkus.start.exception.user.password.InvalidPasswordException;
import com.hzokbe.quarkus.start.exception.user.username.InvalidUsernameException;
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
        var username = dto.getUsername();

        if (username == null || username.isBlank()) {
            throw new InvalidUsernameException("username cannot be null or blank");
        }

        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new InvalidUsernameException("username must contain only letters, numbers, and underscores");
        }

        if (User.count("username", dto.getUsername()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var email = dto.getEmail();

        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("email cannot be null or blank");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidEmailException("invalid email");
        }

        if (User.count("email", dto.getEmail()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var password = dto.getPassword();

        if (password == null || password.isBlank()) {
            throw new InvalidPasswordException("password cannot be null or blank");
        }

        if (password.length() < 8) {
            throw new InvalidPasswordException("password must be at least 8 characters long");
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
