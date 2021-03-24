package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.model.CurrencyConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping("/currency-conversion-service")
public class CurrencyConversionController {

  @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                        @PathVariable String to,
                                                        @PathVariable BigDecimal quantity) {

    HashMap<String, String> params = new HashMap<>();
    params.put("from", from);
    params.put("to", to);

    ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
      .getForEntity(
        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
        CurrencyConversion.class,
        params
      );

    CurrencyConversion responseEntityBody = responseEntity.getBody();

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
