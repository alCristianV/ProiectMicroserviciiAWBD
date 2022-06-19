package com.example.examen.dto;

import com.example.examen.model.Status;
import com.example.examen.model.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

public class CreatePaymentRequestDto {

    @NotBlank
    @Size(max = 20)
    private String type;

    @NotBlank
    @Size(max = 200)
    private String customer;

    @Positive
    @Min(0)
    @NotNull
    private double amount;

    @NotBlank
    @Size(max = 20)
    private String status;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Size(max = 20)
    private String currency;

    public CreatePaymentRequestDto() {
    }

    public CreatePaymentRequestDto(String type, String customer, double amount, String status) {
        this.type = type;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
