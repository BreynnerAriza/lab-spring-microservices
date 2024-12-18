package com.server.eureka.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PersonEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonEurekaServerApplication.class, args);
	}

}
