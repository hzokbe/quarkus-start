package com.hzokbe.quarkus.start.dto.exception.response;

public class ExceptionResponseDTO {
    private final String message;

    public ExceptionResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
