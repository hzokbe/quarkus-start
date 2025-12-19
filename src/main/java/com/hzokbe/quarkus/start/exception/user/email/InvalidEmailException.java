package com.hzokbe.quarkus.start.exception.user.email;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
