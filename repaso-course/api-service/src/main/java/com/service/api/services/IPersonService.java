package com.service.api.services;

import com.service.api.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonService {

    Person getPersonById(Long id);
    Page<Person> getAllPerson(Pageable pageable);
    Person createPerson(Person person);
    Person deletePersonById(Long id);
    Person updatePerson(Person person, Long id);

}
