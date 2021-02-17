package com.udemy.rest.webservices.restfulwebservices.service.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class User {

    Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters.")
    String name;

    @Past
    Date birthDate;

    List<Post> posts;

}
