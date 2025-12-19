package com.hzokbe.quarkus.start.exception.user.password;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
