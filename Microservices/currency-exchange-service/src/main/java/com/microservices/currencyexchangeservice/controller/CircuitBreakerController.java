package com.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircuitBreakerController {

  @GetMapping("/sample-api-retry")
  @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
  //Retries the call n times before throwing an exception
  public String retryController() {
    log.info("Trying connection with wrong url..");
    return new RestTemplate()
      .getForEntity("wrongUrl", String.class)
      .getBody();
  }

  @GetMapping("/sample-api-circuit-breaker")
  @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
  //Stops method execution after a certain amount of failures
  public String circuitBreakerController() {
    log.info("Trying connection with wrong url..");
    return new RestTemplate()
      .getForEntity("wrongUrl", String.class)
      .getBody();
  }


  public String hardCodedResponse(Exception e) { //You can specify other type of exceptions here
    return "Fallback response, error: " + e.getMessage();
  }

}
