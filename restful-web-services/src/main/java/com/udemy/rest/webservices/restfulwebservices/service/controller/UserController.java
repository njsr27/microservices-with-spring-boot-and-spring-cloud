package com.udemy.rest.webservices.restfulwebservices.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.rest.webservices.restfulwebservices.service.dao.UserDaoService;
import com.udemy.rest.webservices.restfulwebservices.service.exception.PostNotFoundException;
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
public class UserController {
    
    final UserDaoService userDaoService;

    final ObjectMapper mapper;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return Optional.ofNullable(userDaoService.findOne(id))
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @PostMapping("/users")
    public ResponseEntity<StatusDetails> createUser(@Valid @RequestBody User user) {
        return Optional.ofNullable(userDaoService.save(user))
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
        if (userDaoService.delete(id))
            return ResponseEntity
                    .ok()
                    .body(
                            StatusDetails.builder()
                                    .message(String.format("User with id %s deleted successfully!", id))
                                    .build()
                    );
        else
            throw new UserNotFoundException(id.toString());
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Integer id) {
        return Optional.ofNullable(userDaoService.findOne(id))
                .map(User::getPosts)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<StatusDetails> createUserPost(@PathVariable Integer userId, @RequestBody Post post) {
        return Optional.ofNullable(userDaoService.save(userId, post))
                .map(modifiedUser ->
                        ResponseEntity
                                .created(
                                        ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(modifiedUser.getId())
                                                .toUri()
                                )
                                .body(
                                        StatusDetails.builder()
                                                .message("Post created successfully!")
                                                .build()
                                )
                ).orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<StatusDetails> deletePost(@PathVariable Integer userId, @PathVariable Integer postId) {
        return Optional.ofNullable(userDaoService.findOne(userId))
                .map(user -> {
                    if (userDaoService.delete(user, postId))
                        return ResponseEntity
                                .ok()
                                .body(
                                        StatusDetails.builder()
                                                .message(String.format("Post with id %d and user id %d deleted successfully!", postId, userId))
                                                .build()
                                );
                    else
                        throw new PostNotFoundException(postId.toString());
                })
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
    }

}
