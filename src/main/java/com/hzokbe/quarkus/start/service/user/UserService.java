package com.hzokbe.quarkus.start.service.user;

import com.hzokbe.quarkus.start.dto.user.UserResponseDTO;
import com.hzokbe.quarkus.start.dto.user.create.response.CreateUserResponseDTO;
import com.hzokbe.quarkus.start.dto.user.create.request.CreateUserRequestDTO;
import com.hzokbe.quarkus.start.dto.user.update.request.UpdateUserRequestDTO;
import com.hzokbe.quarkus.start.dto.user.update.response.UpdateUserResponseDTO;
import com.hzokbe.quarkus.start.exception.UserNotFoundException;
import com.hzokbe.quarkus.start.exception.user.AlreadyRegisteredUserException;
import com.hzokbe.quarkus.start.exception.user.email.InvalidEmailException;
import com.hzokbe.quarkus.start.exception.user.password.InvalidPasswordException;
import com.hzokbe.quarkus.start.exception.user.username.InvalidUsernameException;
import com.hzokbe.quarkus.start.model.user.User;
import com.hzokbe.quarkus.start.service.password.PasswordService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    private final PasswordService passwordService;

    public UserService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Transactional
    public CreateUserResponseDTO create(CreateUserRequestDTO dto) {
        var username = dto.getUsername();

        if (username == null || username.isBlank()) {
            throw new InvalidUsernameException("Username cannot be null or blank");
        }

        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new InvalidUsernameException("Username must contain only letters, numbers, and underscores");
        }

        if (User.count("username", dto.getUsername()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var email = dto.getEmail();

        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("E-mail cannot be null or blank");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidEmailException("Invalid email");
        }

        if (User.count("email", dto.getEmail()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var password = dto.getPassword();

        if (password == null || password.isBlank()) {
            throw new InvalidPasswordException("Password cannot be null or blank");
        }

        if (password.length() < 8) {
            throw new InvalidPasswordException("Password must be at least 8 characters long");
        }

        var user = new User();

        user.username = dto.getUsername();

        user.email = dto.getEmail();

        user.password = passwordService.hash(dto.getPassword());

        user.groups = Set.of("user");

        User.persist(user);

        return new CreateUserResponseDTO(
                user.id,
                user.username,
                user.email
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return User.findAll().stream().map(e -> {
            var user = (User) e;

            return new UserResponseDTO(
                    user.id,
                    user.username,
                    user.email
            );
        }).toList();
    }

    public UserResponseDTO getUserById(UUID id) {
        var optionalUser = User.findByIdOptional(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = (User) optionalUser.get();

        return new UserResponseDTO(
                user.id,
                user.username,
                user.email
        );
    }

    @Transactional
    public UpdateUserResponseDTO updateUser(UUID id, UpdateUserRequestDTO dto) {
        var optionalUser = User.findByIdOptional(id);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var username = dto.getUsername();

        if (username == null || username.isBlank()) {
            throw new InvalidUsernameException("Username cannot be null or blank");
        }

        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new InvalidUsernameException("Username must contain only letters, numbers, and underscores");
        }

        var email = dto.getEmail();

        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("E-mail cannot be null or blank");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidEmailException("Invalid email");
        }

        if (User.count("email", dto.getEmail()) != 0) {
            throw new AlreadyRegisteredUserException();
        }

        var user = (User) optionalUser.get();

        user.username = username;

        user.email = email;

        return new UpdateUserResponseDTO(
                user.id,
                user.username,
                user.email
        );
    }
}
