package com.microservices.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyExchange {

  @Id
  Long id;

  @Column(name = "currency_from")
  String from;

  @Column(name = "currency_to")
  String to;

  BigDecimal conversionMultiple;

  String environment;

}
