package com.kmmx.curso.microservices.Microservices01.test.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kmmx.curso.microservices.Microservices01.models.Person;
import com.kmmx.curso.microservices.Microservices01.repository.PersonRepository;
import com.kmmx.curso.microservices.Microservices01.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  void create() {
    
    final Person entity = new Person("RAUL", "GONZALEZ");
    final Person responseEntity = new Person(99, "RAUL", "GONZALEZ");
    Mockito.when(personRepository.save(entity)).thenReturn(responseEntity);

    final Person personInput = new Person("Raul", "Gonzalez");
    final Person personServiceOutput = personService.create(personInput);

    System.out.println(personServiceOutput);
    Assertions.assertEquals(responseEntity, personServiceOutput);
    
  }
  
}
