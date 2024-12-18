package com.client.api.client;

import com.client.api.models.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "api-service", url = "localhost:8001", path = "/v1/persons")
public interface PersonFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<Person> getPersonById(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<Page<Person>> getAllPersons(Pageable pageable);

    @PostMapping
    ResponseEntity<Person> createPerson(@RequestBody Person person);

    @DeleteMapping("/{id}")
    ResponseEntity<Person> deletePersonById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person);

}
