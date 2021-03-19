package com.udemy.rest.webservices.restfulwebservices.service.dao;

import com.udemy.rest.webservices.restfulwebservices.service.model.Post;
import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import com.udemy.rest.webservices.restfulwebservices.service.repository.PostRepository;
import com.udemy.rest.webservices.restfulwebservices.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserDaoJPAService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PostRepository postRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findOne(Integer id) {
    return userRepository.findById(id);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void delete(Integer id) {
    userRepository.deleteById(id);
  }

  public Optional<User> findPosts(Integer id) {
    return userRepository.findById(id);
  }

  public User savePost(Integer userId, Post post) {
    for (User user : userRepository.findAll()) {
      if (user.getId().equals(userId)) {
        persistPost(
          Post.builder()
            .message(post.getMessage())
            .title(post.getTitle())
            .build(),
          user
        );
        return user;
      }
    }

    return null;
  }

  private void persistPost(Post post, User user) {
    user.getPosts().add(post);
    postRepository.save(post);
    userRepository.save(user);
  }

}
