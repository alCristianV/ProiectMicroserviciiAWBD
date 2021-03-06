package com.example.examen.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment extends RepresentationModel<Payment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Type type;

    @Column
    private String customer;

    @Column
    private double amount;

    @Column
    private CurrencyValues currency;

    public CurrencyValues getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyValues currency) {
        this.currency = currency;
    }

    @Column
    private Status status;

    public Payment() {
    }

    public Payment(long id, Type type, String customer, double amount, Status status, CurrencyValues currency) {
        this.id = id;
        this.type = type;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
        this.currency = currency;
    }

    public Payment(Type type, String customer, double amount, Status status, CurrencyValues currency) {
        this.type = type;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
