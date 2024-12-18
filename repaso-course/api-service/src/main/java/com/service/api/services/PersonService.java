package com.service.api.services;

import com.service.api.models.Person;
import com.service.api.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService{

    private final PersonRepository personRepository;

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Person> getAllPerson(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person deletePersonById(Long id) {
        Person person = getPersonById(id);
        if (person != null) { personRepository.delete(person); }
        return person;
    }

    @Override
    public Person updatePerson(Person person, Long id) {
        Person personBd = getPersonById(id);
        if (personBd != null) {
            personBd.setName(person.getName());
            personBd.setSurname(person.getSurname());
            personBd.setEmail(person.getEmail());
            personBd.setAge(person.getAge());
            personBd = personRepository.save(personBd);
        }
        return personBd;
    }

}
