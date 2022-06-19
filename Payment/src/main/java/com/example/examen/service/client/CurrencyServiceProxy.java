package com.example.examen.service.client;

import com.example.examen.model.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "currency")
public interface CurrencyServiceProxy
{
    @GetMapping(value="/currency", consumes = "application/json")
    Currency findCurrency();
}