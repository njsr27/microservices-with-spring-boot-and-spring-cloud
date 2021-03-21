package com.microservices.limitsservice.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Limits {

  Integer maximum;

  Integer minimum;

}
