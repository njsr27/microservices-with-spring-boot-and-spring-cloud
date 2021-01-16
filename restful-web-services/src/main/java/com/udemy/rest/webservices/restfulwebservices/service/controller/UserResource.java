package com.udemy.rest.webservices.restfulwebservices.service.controller;

import java.util.List;

import com.udemy.rest.webservices.restfulwebservices.service.dao.UserDaoService;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserResource {

    final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return userDaoService.findOne(id);
    }

}
