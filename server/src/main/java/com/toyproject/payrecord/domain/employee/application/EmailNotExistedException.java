package com.toyproject.payrecord.domain.employee.application;

public class EmailNotExistedException extends RuntimeException {
    public EmailNotExistedException() {
        super();
    }

    public EmailNotExistedException(String message) {
        super(message);
    }

    public EmailNotExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotExistedException(Throwable cause) {
        super(cause);
    }
}
