package com.udemy.rest.webservices.restfulwebservices.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilteredBean {

    String value1;

    String value2;

    @JsonIgnore
    String importantPassword;

}
