package com.shermatov.ecommerce.authservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public record ExceptionsHandler() {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistExceptionHandler(EmailAlreadyExistException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("email", ex.getMessage()));
    }

    @ExceptionHandler(CredentialsIsNotValidException.class)
    public ResponseEntity<Map<String, String>> credentialsIsNotValidExceptionHandler(CredentialsIsNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("login", ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptionHandler(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(((FieldError)error).getField(), error.getDefaultMessage());
        });

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleOtherExceptionsHandler(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body("Server error, please try request after 2-3 minutes");
    }
}
