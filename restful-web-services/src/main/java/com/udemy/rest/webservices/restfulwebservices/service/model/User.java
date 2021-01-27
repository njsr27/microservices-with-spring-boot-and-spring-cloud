package com.udemy.rest.webservices.restfulwebservices.service.model;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    Integer id;

    String name;

    Date birthDate;

    List<Post> posts;

}
