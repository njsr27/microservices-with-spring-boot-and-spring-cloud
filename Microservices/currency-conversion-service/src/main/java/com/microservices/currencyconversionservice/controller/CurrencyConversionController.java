package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import com.microservices.currencyconversionservice.rest.CurrencyExchangeProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency-conversion-service")
public class CurrencyConversionController {

  private final CurrencyExchangeProxy currencyExchangeProxy;

  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                        @PathVariable String to,
                                                        @PathVariable BigDecimal quantity) {
    
    CurrencyConversion responseEntityBody = currencyExchangeProxy.retrieveExchangeValue(from, to);

    return CurrencyConversion.builder()
      .id(responseEntityBody.getId())
      .from(from)
      .to(to)
      .quantity(quantity)
      .conversionMultiple(responseEntityBody.getConversionMultiple())
      .environment(responseEntityBody.getEnvironment())
      .totalCalculatedAmount(quantity.multiply(responseEntityBody.getConversionMultiple()))
      .build();
  }
}
