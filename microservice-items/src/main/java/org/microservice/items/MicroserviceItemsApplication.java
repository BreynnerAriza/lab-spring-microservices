package org.microservice.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceItemsApplication{

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceItemsApplication.class, args);
	}

}
