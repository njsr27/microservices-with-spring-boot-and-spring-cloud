package com.udemy.rest.webservices.restfulwebservices.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User with id %s not found";

    public UserNotFoundException(String id) {
        super(String.format(MESSAGE, id));
        log.error(String.format(MESSAGE, id));
    }
}
