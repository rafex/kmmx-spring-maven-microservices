package com.kmmx.curso.microservices.Microservices01.repository;

import com.kmmx.curso.microservices.Microservices01.models.Person2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository2 extends CrudRepository<Person2, Long> {
}
