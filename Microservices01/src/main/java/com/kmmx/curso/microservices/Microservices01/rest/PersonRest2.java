package com.kmmx.curso.microservices.Microservices01.rest;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.models.Person2;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person2> get(){
        Iterable<Person2> getPersons = personRepository2.findAll();
        return getPersons;
    }

    // /api/person2/{id} == /api/person2/50
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person2> getPersonForId(@PathVariable long id) {
        Optional<Person2> getPerson = personRepository2.findById(id);
        return getPerson;
    }
}
