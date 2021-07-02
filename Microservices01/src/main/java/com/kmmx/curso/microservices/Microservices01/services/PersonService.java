package com.kmmx.curso.microservices.Microservices01.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository;

@Service
public class PersonService {
  
  @Autowired
  private PersonRepository personRepository;
  
  public Boolean delete(final Long id) {
    final Person person = new Person();
    person.setId(id);
    
    personRepository.delete(person);
    
    final Optional<Person> deletePerson = personRepository.findById(person.getId());
    
    if (deletePerson.isEmpty()) {
      return true;
    }
    
    return false;
  }
  
  public Person create(final Person person) {
    
    person.setFirstName(person.getFirstName().toUpperCase());
    person.setLastName(person.getLastName().toUpperCase());
    
    final Person addPerson = personRepository.save(person);
    
    return addPerson;
  }
  
  public Iterable<Person> all() {
    return personRepository.findAll();
  }
  
  public Person findId(final Person person) {
    
    if (person != null) {
      if (person.getId() > 0) {
        final Optional<Person> findPerson = personRepository.findById(person.getId());
        return findPerson != null ? findPerson.get() : null;
      }
    }
    
    return null;
  }
  
  public List<Person> findName(final Person person) {
    person.setFirstName(person.getFirstName().toUpperCase());
    return personRepository.findByFirstName(person.getFirstName());
  }
  
  public Set<Person> findName(final String query) {
    
    final List<Person> listForName = personRepository.findByFirstName(query.toUpperCase());
    final List<Person> listForLastName = personRepository.findByLastName(query.toUpperCase());
    
    final Set<Person> resultList = new HashSet<>();
    
    for (final Person person : listForName) {
      resultList.add(person);
    }
    
    for (final Person person : listForLastName) {
      resultList.add(person);
    }
    
    return resultList;
  }
  
  public Person update(final Person person) {
    
    if (person != null) {
      final Optional<Person> findPerson = personRepository.findById(person.getId());
      if (!findPerson.isEmpty()) {
        person.setFirstName(person.getFirstName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());
        return personRepository.save(person);
      }
    }
    
    return null;
  }
  
}
