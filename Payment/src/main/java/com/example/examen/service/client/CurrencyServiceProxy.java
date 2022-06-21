package com.example.examen.service.client;

import com.example.examen.model.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = "currency-ron")
@FeignClient(value = "${feign.value}")
public interface CurrencyServiceProxy
{
    @GetMapping(value="/currency", consumes = "application/json")
    Currency findCurrency();
}