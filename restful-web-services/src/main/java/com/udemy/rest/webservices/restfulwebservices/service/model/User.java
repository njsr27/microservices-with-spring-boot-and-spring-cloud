package com.udemy.rest.webservices.restfulwebservices.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Value
@Builder
@ApiModel(description = "All details about the user.")
public class User {

    Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters.")
    @ApiModelProperty(notes = "Name should have at least 2 characters.")
    String name;

    @Past
    @ApiModelProperty(notes = "Birthdate cannot be in the future.")
    Date birthDate;

    List<Post> posts;

}
