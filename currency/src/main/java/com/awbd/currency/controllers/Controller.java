package com.awbd.currency.controllers;

import com.awbd.currency.config.PropertiesConfig;
import com.awbd.currency.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private PropertiesConfig configuration;

    @GetMapping("/currency")
    public Currency getCurrency() {
        return new Currency(configuration.getValue());
    }
}