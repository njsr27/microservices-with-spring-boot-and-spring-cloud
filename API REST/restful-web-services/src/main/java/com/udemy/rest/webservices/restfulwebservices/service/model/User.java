package com.udemy.rest.webservices.restfulwebservices.service.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the user.")
@Entity
public class User {

  @Id
  @GeneratedValue
  Integer id;

  @Size(min = 2, message = "Name should have at least 2 characters.")
  @ApiModelProperty(notes = "Name should have at least 2 characters.")
  String name;

  @Past
  @ApiModelProperty(notes = "Birthdate cannot be in the future.")
  Date birthDate;

  @ElementCollection
  List<Post> posts;

}
