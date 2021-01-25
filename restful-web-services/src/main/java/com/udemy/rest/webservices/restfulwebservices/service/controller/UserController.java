package com.udemy.rest.webservices.restfulwebservices.service.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.udemy.rest.webservices.restfulwebservices.service.dao.UserDaoService;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    final UserDaoService userDaoService;

    final ObjectMapper mapper;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    @SneakyThrows
    public User getUser(@PathVariable Integer id) {
        return Optional.ofNullable(userDaoService.findOne(id))
            .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return Optional.ofNullable(userDaoService.save(user))
            .map(createdUser ->
                ResponseEntity.created(
                    ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdUser.getId())
                        .toUri()
                ).build()
            )
            .orElseGet(() -> {
                log.error("Error during user creation!");
                return ResponseEntity
                    .status(500)
                    .body("Error during user creation!");
            });

        //log.info("User created successfully, new list: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDaoService.findAll()));

    }

}
