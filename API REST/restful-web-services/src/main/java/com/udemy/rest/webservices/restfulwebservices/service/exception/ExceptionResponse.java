package com.udemy.rest.webservices.restfulwebservices.service.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {

  private Date timestamp;

  private String message;

  private String details;

  public ExceptionResponse(final Date timestamp, final String message, final String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }
}
