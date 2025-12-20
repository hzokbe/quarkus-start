package com.hzokbe.quarkus.start.service.auth;

import com.hzokbe.quarkus.start.dto.auth.sign.in.SignInRequestDTO;
import com.hzokbe.quarkus.start.exception.UserNotFoundException;
import com.hzokbe.quarkus.start.exception.auth.InvalidCredentialsException;
import com.hzokbe.quarkus.start.model.user.User;
import com.password4j.Password;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class AuthService {
    public String signIn(SignInRequestDTO dto) {
        var optionalUser = User.find("username", dto.getUsername()).firstResultOptional();

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        var user = (User) optionalUser.get();

        if (!Password.check(dto.getPassword(), user.password).withArgon2()) {
            throw new InvalidCredentialsException();
        }

        return Jwt
                .issuer("quarkus-start")
                .claim("sub", dto.getUsername())
                .groups(user.groups)
                .expiresIn(Duration.ofHours(1))
                .sign();
    }
}
