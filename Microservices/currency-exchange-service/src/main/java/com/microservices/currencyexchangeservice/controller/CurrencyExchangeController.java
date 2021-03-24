package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.Repository.CurrencyExchangeRepository;
import com.microservices.currencyexchangeservice.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

  private final Environment environment;

  private final CurrencyExchangeRepository currencyExchangeRepository;

  @GetMapping("/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                @PathVariable String to) {
    return Optional.ofNullable(currencyExchangeRepository.findByFromAndTo(from, to))
      .map(currencyExchange -> {
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
      }).orElse(null);
  }

}
