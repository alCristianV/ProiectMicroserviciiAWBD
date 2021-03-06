package com.example.examen.mapper;

import com.example.examen.dto.CreatePaymentRequestDto;
import com.example.examen.exception.PaymentInvalidStatusException;
import com.example.examen.exception.PaymentInvalidTypeException;
import com.example.examen.model.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment createPaymentRequestDtoToPayment(CreatePaymentRequestDto request) {

        Status status = checkPaymentStatus(request.getStatus().toUpperCase());
        CurrencyValues currency = checkCurrencyStatus(request.getCurrency().toUpperCase());
        Type type = checkPaymentType(request.getType().toUpperCase());

        return new Payment(type, request.getCustomer(), request.getAmount(), status, currency);
    }

    private Status checkPaymentStatus(String status) {
        Status s;
        try {
            s = Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new PaymentInvalidStatusException();
        }
        return s;
    }

    private CurrencyValues checkCurrencyStatus(String currency) {
        CurrencyValues c;
        try {
            c = CurrencyValues.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new PaymentInvalidStatusException();
        }
        return c;
    }

    private Type checkPaymentType(String type) {
        Type t;
        try {
            t = Type.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new PaymentInvalidTypeException();
        }
        return t;
    }
}
