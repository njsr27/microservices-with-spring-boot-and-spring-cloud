package com.udemy.rest.webservices.restfulwebservices.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {

  private static final String MESSAGE = "Post with id %s not found";

  public PostNotFoundException(String id) {
    super(String.format(MESSAGE, id));
    log.error(String.format(MESSAGE, id));
  }

}
