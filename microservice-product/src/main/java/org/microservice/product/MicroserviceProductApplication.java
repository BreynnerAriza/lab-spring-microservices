package org.microservice.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"org.microservice.commond.libs.entities.Product"})
public class MicroserviceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProductApplication.class, args);
    }

}
