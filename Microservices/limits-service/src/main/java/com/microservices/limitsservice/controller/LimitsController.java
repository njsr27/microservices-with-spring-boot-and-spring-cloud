package com.microservices.limitsservice.controller;

import com.microservices.limitsservice.configuration.LimitsServiceConfiguration;
import com.microservices.limitsservice.model.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {

  private final LimitsServiceConfiguration limitsServiceConfiguration;

  @GetMapping("/limits")
  public Limits retrieveLimits() {
    return Limits.builder()
      .minimum(limitsServiceConfiguration.getMinimum())
      .maximum(limitsServiceConfiguration.getMaximum())
      .build();
  }

}
