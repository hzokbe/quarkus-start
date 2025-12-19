package com.hzokbe.quarkus.start.service.password;

import com.password4j.Password;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PasswordService {
    public String hash(String password) {
        return Password.hash(password).addRandomSalt(32).withArgon2().getResult();
    }
}
