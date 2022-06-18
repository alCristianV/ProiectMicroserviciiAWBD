package com.example.examen.exception;

public class PaymentNotExistException extends RuntimeException {

    public PaymentNotExistException() {
        super("The payment does not exist");
    }
}
