package com.udemy.rest.webservices.restfulwebservices.service.model;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    Integer id;

    String name;

    Date birthDate;

}
