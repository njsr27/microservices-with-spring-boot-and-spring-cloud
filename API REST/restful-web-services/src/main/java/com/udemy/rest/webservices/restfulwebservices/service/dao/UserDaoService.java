package com.udemy.rest.webservices.restfulwebservices.service.dao;

import com.google.common.collect.Lists;
import com.udemy.rest.webservices.restfulwebservices.service.model.Post;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserDaoService {

  static List<User> users;

  private static int usersCount = 0;

  private static int postsCount = 0;

  static {
    users = Lists.newArrayList(
      User.builder().id(++usersCount).name("User 123").birthDate(new Date()).posts(Lists.newArrayList()).build(),
      User.builder().id(++usersCount).name("User 456").birthDate(new Date()).posts(Lists.newArrayList()).build(),
      User.builder().id(++usersCount).name("User 789").birthDate(new Date()).posts(Lists.newArrayList()).build()
    );
  }

  public List<User> findAll() {
    return users;
  }

  public User save(User baseUser) {
    return Optional.of(
      User.builder()
        .id(++usersCount)
        .name(baseUser.getName())
        .birthDate(baseUser.getBirthDate())
        .posts(Lists.newArrayList())
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

  public Boolean delete(Integer id) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id)) {
        users.remove(i);
        return true;
      }
    }

    return false;
  }

  public User save(Integer userId, Post post) {
    for (User user : users) {
      if (user.getId().equals(userId)) {
        user.getPosts().add(
          Post.builder()
            .message(post.getMessage())
            .title(post.getTitle())
            .id(postsCount++)
            .build()
        );
        return user;
      }
    }

    return null;
  }

  public Boolean delete(User user, Integer postId) {
    for (int i = 0; i < user.getPosts().size(); i++) {
      if (user.getPosts().get(i).getId().equals(postId)) {
        user.getPosts().remove(i);
        return true;
      }
    }

    return false;
  }

}
