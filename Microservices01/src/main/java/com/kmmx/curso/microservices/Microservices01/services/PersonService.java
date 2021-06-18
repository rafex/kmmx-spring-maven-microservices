package com.kmmx.curso.microservices.Microservices01.services;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Boolean delete(Long id) {
        Person person = new Person();
        person.setId(id);

        personRepository.delete(person);

        Optional<Person> deletePerson = personRepository.findById(person.getId());

        if (deletePerson.isEmpty()) {
            return true;
        }

        return false;
    }

    public Person create(Person person) {

        person.setFirstName(person.getFirstName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());

        Person addPerson = personRepository.save(person);

        return addPerson;
    }

    public Iterable<Person> all() {
        return personRepository.findAll();
    }

    public Person findId(Person person) {

        if (person != null) {
            if (person.getId() > 0) {
                Optional<Person> findPerson = personRepository.findById(person.getId());
                return findPerson != null ? findPerson.get() : null;
            }
        }

        return null;
    }

    public List<Person> findName(Person person) {
        person.setFirstName(person.getFirstName().toUpperCase());
        return personRepository.findByFirstName(person.getFirstName());
    }

    public Set<Person> findName(String query) {

        List<Person> listForName = personRepository.findByFirstName(query.toUpperCase());
        List<Person> listForLastName = personRepository.findByLastName(query.toUpperCase());

        Set<Person> resultList = new HashSet<>();

        for (Person person : listForName) {
            resultList.add(person);
        }

        for (Person person : listForLastName) {
            resultList.add(person);
        }

        return resultList;
    }

    public Person update(Person person) {

        if (person != null) {
            Optional<Person> findPerson = personRepository.findById(person.getId());
            if (!findPerson.isEmpty()) {
                person.setFirstName(person.getFirstName().toUpperCase());
                person.setLastName(person.getLastName().toUpperCase());
                return personRepository.save(person);
            }
        }

        return null;
    }

}
