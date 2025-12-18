package com.hzokbe.quarkus.start.exception.user;

public class AlreadyRegisteredUserException extends RuntimeException {
    public AlreadyRegisteredUserException() {
        super("Already registered user");
    }
}
