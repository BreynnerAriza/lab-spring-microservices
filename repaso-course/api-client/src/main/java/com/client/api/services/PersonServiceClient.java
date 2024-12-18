package com.client.api.services;

import com.client.api.client.PersonFeignClient;
import com.client.api.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceClient implements IPersonServiceClient {

    private final PersonFeignClient personFeignClient;

    private static final String SERVICE_TYPE = "Person client";

    @Override
    public Person getPersonById(Long id) {
        Person person = personFeignClient.getPersonById(id).getBody();
        if (person == null) return null;
        person.setService(SERVICE_TYPE);
        return person;
    }

    @Override
    public Page<Person> getAllPerson(Pageable pageable) {
        Page<Person> pagePerson = personFeignClient.getAllPersons(pageable).getBody();
        if (pagePerson == null) return null;
        pagePerson.get().forEach(p -> p.setService(SERVICE_TYPE));
        return pagePerson;
    }

    @Override
    public Person createPerson(Person person) {
        Person personCreated = personFeignClient.createPerson(person).getBody();
        if (personCreated == null) return null;
        personCreated.setService(SERVICE_TYPE);
        return personCreated;
    }

    @Override
    public Person deletePersonById(Long id) {
        Person personDelete = personFeignClient.deletePersonById(id).getBody();
        if (personDelete == null) return null;
        personDelete.setService(SERVICE_TYPE);
        return personDelete;
    }

    @Override
    public Person updatePerson(Person person, Long id) {
        Person personUpdate = personFeignClient.updatePerson(id, person).getBody();
        if (personUpdate == null) return null;
        personUpdate.setService(SERVICE_TYPE);
        return personUpdate;
    }
}
