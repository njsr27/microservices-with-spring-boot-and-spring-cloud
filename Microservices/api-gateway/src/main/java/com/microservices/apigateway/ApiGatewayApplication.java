package com.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This kind of ApiGateway implementation is useful for things that are common for all your microservices,
// such as authentication, security, metrics, etc. It's also useful for endpoint routing.
@SpringBootApplication
public class  ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
