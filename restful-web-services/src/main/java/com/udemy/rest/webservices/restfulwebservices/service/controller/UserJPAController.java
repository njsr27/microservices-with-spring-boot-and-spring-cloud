package com.udemy.rest.webservices.restfulwebservices.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.rest.webservices.restfulwebservices.service.dao.UserDaoJPAService;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/JPA")
public class UserJPAController {

    final UserDaoJPAService userDaoJPAService;

    final ObjectMapper mapper;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDaoJPAService.findAll();
    }

}
