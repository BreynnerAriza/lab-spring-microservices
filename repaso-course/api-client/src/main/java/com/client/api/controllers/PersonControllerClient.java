package com.client.api.controllers;

import com.client.api.models.Person;
import com.client.api.services.IPersonServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/persons-client")
@RequiredArgsConstructor
public class PersonControllerClient {

    private final IPersonServiceClient personServiceClient;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personServiceClient.getPersonById(id);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<Page<Person>> getAllPerson(Pageable pageable) {
        Page<Person> person = personServiceClient.getAllPerson(pageable);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personServiceClient.createPerson(person);
        return ResponseEntity.ok(createdPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable Long id) {
        Person person = personServiceClient.deletePersonById(id);
        return ResponseEntity.ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person personUpdate = personServiceClient.updatePerson(person, id);
        return ResponseEntity.ok(personUpdate);
    }

}
