package com.udemy.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import lombok.Data;

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
