package org.microservice.commond.libs.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTS")
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @Transient
    private int port;


}
