package com.udemy.rest.webservices.restfulwebservices.service.config;

import com.sun.tools.javac.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class Swagger {

  public static final Contact DEFAULT_CONTACT = new Contact(
    "Nicolas Saavedra",
    "https://www.linkedin.com/in/nicolassaavedrarojas/",
    "njsr27@gmail.com");

  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
    "Awesome Api Title",
    "Awesome Api Description",
    "1.0",
    "urn:tos",
    DEFAULT_CONTACT,
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0",
    new ArrayList<>());

  public static final Set<String> CONSUMES_PRODUCES_DEFAULT = new HashSet<>(List.of("application/json", "application/xml"));

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(DEFAULT_API_INFO)
      .produces(CONSUMES_PRODUCES_DEFAULT)
      .consumes(CONSUMES_PRODUCES_DEFAULT);
  }
}
