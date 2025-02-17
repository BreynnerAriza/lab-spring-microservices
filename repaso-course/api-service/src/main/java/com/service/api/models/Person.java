package com.service.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "persons")
public class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    @Column(name = "init_date")
    private LocalDate initDate;
    private String email;
    @Transient
    private String service = "Api service persons";

}
