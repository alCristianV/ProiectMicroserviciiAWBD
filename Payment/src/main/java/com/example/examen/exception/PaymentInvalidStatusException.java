package com.example.examen.exception;

public class PaymentInvalidStatusException extends RuntimeException {

    public PaymentInvalidStatusException() {
        super("The value provided for status is invalid. It should be one of: NEW, PROCESSED, CANCELLED");
    }
}
