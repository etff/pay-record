package com.toyproject.payrecord.domain.employee.application;

public class EmailExistedException extends RuntimeException {

    public EmailExistedException() {
        super();
    }

    public EmailExistedException(String message) {
        super(message);
    }

    public EmailExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExistedException(Throwable cause) {
        super(cause);
    }
}
