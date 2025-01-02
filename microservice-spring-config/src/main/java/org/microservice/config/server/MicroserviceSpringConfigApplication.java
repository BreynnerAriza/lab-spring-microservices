package org.microservice.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroserviceSpringConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSpringConfigApplication.class, args);
	}

}
