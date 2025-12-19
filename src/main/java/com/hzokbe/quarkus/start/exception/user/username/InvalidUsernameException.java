package com.hzokbe.quarkus.start.exception.user.username;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
