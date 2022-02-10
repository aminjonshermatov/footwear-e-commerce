package com.shermatov.ecommerce.authservice.exception;

public class CredentialsIsNotValidException extends IllegalStateException {

    private static final String MESSAGE = "Email or password didn't match";

    public CredentialsIsNotValidException() {
        super(MESSAGE);
    }
}
