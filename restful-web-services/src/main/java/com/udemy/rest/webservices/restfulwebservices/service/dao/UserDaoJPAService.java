package com.udemy.rest.webservices.restfulwebservices.service.dao;

import com.udemy.rest.webservices.restfulwebservices.service.model.User;
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

}
