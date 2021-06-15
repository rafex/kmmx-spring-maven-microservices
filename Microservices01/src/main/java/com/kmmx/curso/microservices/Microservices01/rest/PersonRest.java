package com.kmmx.curso.microservices.Microservices01.rest;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/person")
public class PersonRest {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ArrayList<Person> findAll(){
        Iterable<Person> personIterable = personRepository.findAll();
        ArrayList<Person> persons = new ArrayList<>();
        //personIterable.forEach(persons::add);
        personIterable.forEach(person -> {
            persons.add(person);
        });
        return persons;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        System.out.println("Request: " + person);
        Person newPerson = personRepository.save(person);
        return newPerson;
    }
}
