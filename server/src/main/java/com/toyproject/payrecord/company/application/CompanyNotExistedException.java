package com.toyproject.payrecord.company.application;

public class CompanyNotExistedException extends RuntimeException {

    public CompanyNotExistedException() {
        super();
    }

    public CompanyNotExistedException(String message) {
        super(message);
    }

    public CompanyNotExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyNotExistedException(Throwable cause) {
        super(cause);
    }
}
