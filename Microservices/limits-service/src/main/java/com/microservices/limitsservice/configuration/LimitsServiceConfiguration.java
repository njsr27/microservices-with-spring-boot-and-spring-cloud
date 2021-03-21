package com.microservices.limitsservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("limits-service")
public class LimitsServiceConfiguration {

  Integer minimum;

  Integer maximum;
  
}
