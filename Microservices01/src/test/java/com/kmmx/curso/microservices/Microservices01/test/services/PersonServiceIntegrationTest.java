package com.kmmx.curso.microservices.Microservices01.test.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.services.PersonService;

@SpringBootTest
public class PersonServiceIntegrationTest {
  
  @Autowired
  private PersonService personService;

  @Test
  void create() {
    final Person personInput = new Person("Raul", "Gonzalez");
    final Person personOutput = personService.create(personInput);
    
    Assertions.assertNotNull(personOutput.getId());
    Assertions.assertTrue(personOutput.getId() > 0);
  }

}
