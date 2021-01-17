package com.udemy.rest.webservices.restfulwebservices.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDaoService {

    static List<User> users;

    private static int usersCount = 0;

    static {
        users = Lists.newArrayList(
            User.builder().id(++usersCount).name("User 123").birthDate(new Date()).build(),
            User.builder().id(++usersCount).name("User 456").birthDate(new Date()).build(),
            User.builder().id(++usersCount).name("User 789").birthDate(new Date()).build()
        );
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User baseUser) {
        return Optional.of(
            User.builder()
                .name(baseUser.getName())
                .birthDate(baseUser.getBirthDate())
                .id(++usersCount)
                .build()
        ).map(newUser -> {
            users.add(newUser);
            return newUser;
        }).get();
    }

    public User findOne(Integer id) {
        return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst()
            .orElseGet(() -> {
                log.error("User not found!");
                return null;
            });
    }

}
