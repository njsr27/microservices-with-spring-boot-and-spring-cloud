package com.microservices.currencyconversionservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {

  Integer id;

  String from;

  String to;

  BigDecimal conversionMultiple;

  BigDecimal quantity;

  BigDecimal totalCalculatedAmount;

  String environment;

}
