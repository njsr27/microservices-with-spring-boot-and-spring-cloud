package com.udemy.rest.webservices.restfulwebservices.service.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Post {

    Integer id;

    String title;

    String message;

}
