package com.client.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Person implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private LocalDate initDate;
    private String email;
    private String service;

}
