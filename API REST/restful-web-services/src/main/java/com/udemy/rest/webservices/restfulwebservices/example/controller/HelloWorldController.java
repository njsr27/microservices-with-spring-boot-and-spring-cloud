package com.udemy.rest.webservices.restfulwebservices.example.controller;

import com.udemy.rest.webservices.restfulwebservices.example.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello world!";
  }

  @GetMapping("/helloWorldBean")
  public HelloWorldBean helloWorldBean() {
    return HelloWorldBean.builder().message("Hello world!").build();
  }

  @GetMapping("/hello/{name}")
  public String helloName(@PathVariable String name) {
    return String.format("Hello %s!", name);
  }

}
