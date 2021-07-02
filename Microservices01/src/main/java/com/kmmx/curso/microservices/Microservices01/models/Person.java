package com.kmmx.curso.microservices.Microservices01.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @Column(nullable = false)
  private String firstName;
  
  @Column(nullable = false)
  private String lastName;
  
  public Person() {
    super();
  }
  
  /**
   * @param id
   * @param firstName
   * @param lastName
   */
  public Person(final long id, final String firstName, final String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public Person(final String firstName, final String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(final long id) {
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }
  
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Person person = (Person) o;
    return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
  
  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
  }
}
