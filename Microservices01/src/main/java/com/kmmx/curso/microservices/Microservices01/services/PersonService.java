package com.kmmx.curso.microservices.Microservices01.services;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Boolean delete(Long id) {
        Person person = new Person();
        person.setId(id);

        personRepository.delete(person);

        Optional<Person> deletePerson = personRepository.findById(person.getId());

        if(deletePerson.isEmpty()){
            return true;
        }

        return false;
    }

    public Person create(Person person){

        person.setName(person.getName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());

        Person addPerson = personRepository.save(person);

        return addPerson;
    }

    public Iterable<Person> all(){
        return personRepository.findAll();
    }

    public Person findId(Person person){

        if(person != null){
            if(person.getId() > 0){
                Optional<Person> findPerson = personRepository.findById(person.getId());
                return findPerson != null ? findPerson.get() : null;
            }
        }

        return null;
    }

    public List<Person> findName(Person person) {
        person.setName(person.getName().toUpperCase());
        return personRepository.findByName(person.getName());
    }

}
