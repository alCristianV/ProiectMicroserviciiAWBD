package com.example.examen.exception;

public class PaymentCancelledException extends RuntimeException {

    public PaymentCancelledException() {
        super("The payment is already cancelled");
    }
}
