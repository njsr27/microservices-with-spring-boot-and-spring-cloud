package com.udemy.rest.webservices.restfulwebservices.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.rest.webservices.restfulwebservices.service.dao.UserDaoJPAService;
import com.udemy.rest.webservices.restfulwebservices.service.exception.UserNotFoundException;
import com.udemy.rest.webservices.restfulwebservices.service.model.Post;
import com.udemy.rest.webservices.restfulwebservices.service.model.StatusDetails;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return userDaoJPAService.findOne(id)
            .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @PostMapping("/users")
    public ResponseEntity<StatusDetails> createUser(@Valid @RequestBody User user) {
        return Optional.ofNullable(userDaoJPAService.save(user))
            .map(createdUser ->
                ResponseEntity
                    .created(
                        ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdUser.getId())
                            .toUri()
                    )
                    .body(
                        StatusDetails.builder()
                            .message("User created successfully!")
                            .build()
                    )
            )
            .orElseGet(() -> {
                log.error("Error during user creation!");
                return ResponseEntity
                    .status(500)
                    .body(
                        StatusDetails.builder()
                            .message("Error during user creation!")
                            .build()
                    );
            });
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        userDaoJPAService.delete(id);
        return ResponseEntity
            .ok()
            .body(
                StatusDetails.builder()
                    .message(String.format("User with id %s deleted successfully!", id))
                    .build()
            );
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Integer id) {
        return userDaoJPAService.findPosts(id)
            .map(User::getPosts)
            .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }
}
