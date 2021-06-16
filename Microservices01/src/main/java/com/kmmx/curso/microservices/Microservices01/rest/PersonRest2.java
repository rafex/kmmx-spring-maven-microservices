package com.kmmx.curso.microservices.Microservices01.rest;

import com.kmmx.curso.microservices.Microservices01.models.Person2;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person2")
public class PersonRest2 {

    @Autowired
    private PersonRepository2 personRepository2;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person2 create(@RequestBody Person2 person2) {
        Person2 newPerson = personRepository2.save(person2);
        return newPerson;
    }
}
