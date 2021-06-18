package com.kmmx.curso.microservices.Microservices01.rest;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
public class PersonRest {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Person> findAll(){
        return personService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        System.out.println("Request: " + person);
        Person newPerson = personService.create(person);
        return newPerson;
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> forName(@PathVariable String name) {
        Person person = new Person();
        person.setFirstName(name);
        System.out.println("Request: " + person);
        List<Person> listPerson = personService.findName(person);
        return listPerson;
    }

    @GetMapping("/name2/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Person> forName2(@PathVariable String name) {
        System.out.println("Request: " + name);
        Set<Person> listPerson = personService.findName(name);
        return listPerson;
    }

    // /api/person2/{id} == /api/person2/50
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person getPersonForId(@PathVariable long id) {
        Person find = new Person();
        find.setId(id);
        Person getPerson = personService.findId(find);
        return getPerson;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable Long id){

        return personService.delete(id) ? "Eliminado" : "Algo fallo";

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Person update(@RequestBody Person person) {
        System.out.println("Request: " + person);
        Person updatePerson = personService.update(person);
        return updatePerson;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person update2(@PathVariable Long id,@RequestBody Person person) {
        person.setId(id.longValue());
        System.out.println("Request: " + person);
        Person updatePerson = personService.update(person);
        return updatePerson;
    }

}
