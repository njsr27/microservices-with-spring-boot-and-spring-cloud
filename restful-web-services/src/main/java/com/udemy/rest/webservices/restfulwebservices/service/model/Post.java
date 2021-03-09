package com.udemy.rest.webservices.restfulwebservices.service.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about the posts of a user.")
@Entity
public class Post {

  @Id
  @GeneratedValue
  Integer id;

  String title;

  String message;

}
