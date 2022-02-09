package com.shermatov.ecommerce.authservice.exception;

public class EmailAlreadyExistException extends IllegalStateException {

    private static final String MESSAGE = "Email already exists";

    public EmailAlreadyExistException() {
        super(MESSAGE);
    }
}
