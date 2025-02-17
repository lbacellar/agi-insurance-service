package br.com.agi.application.exception;

import org.springframework.http.HttpStatus;

public class ApiServiceUnavailableException extends RuntimeException {
    private final HttpStatus status;

    public ApiServiceUnavailableException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
