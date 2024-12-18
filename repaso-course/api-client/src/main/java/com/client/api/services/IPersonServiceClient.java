package com.client.api.services;

import com.client.api.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonServiceClient {

    Person getPersonById(Long id);
    Page<Person> getAllPerson(Pageable pageable);
    Person createPerson(Person person);
    Person deletePersonById(Long id);
    Person updatePerson(Person person, Long id);

}
