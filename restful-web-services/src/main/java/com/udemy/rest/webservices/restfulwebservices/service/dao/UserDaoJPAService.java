package com.udemy.rest.webservices.restfulwebservices.service.dao;

import com.udemy.rest.webservices.restfulwebservices.service.model.User;
import com.udemy.rest.webservices.restfulwebservices.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserDaoJPAService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
