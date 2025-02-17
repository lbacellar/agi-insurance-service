package br.com.agi.application.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public CustomerNotFoundException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
