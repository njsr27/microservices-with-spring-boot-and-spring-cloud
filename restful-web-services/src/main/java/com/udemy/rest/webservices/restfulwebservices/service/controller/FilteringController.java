package com.udemy.rest.webservices.restfulwebservices.service.controller;

import com.udemy.rest.webservices.restfulwebservices.service.model.FilteredBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public FilteredBean getExample() {
        return new FilteredBean("value1", "value2", "password");
    }
}
