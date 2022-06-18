package com.example.examen.exception;

public class PaymentInvalidTypeException extends RuntimeException {

    public PaymentInvalidTypeException() {
        super("The value provided for type is invalid. It should be one of: ONLINE, POS");
    }
}
