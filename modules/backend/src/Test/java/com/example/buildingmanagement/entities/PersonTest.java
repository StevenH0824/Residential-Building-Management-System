package com.example.buildingmanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonTest {
  Person person;

  @BeforeEach
  void setUp() {
    person = new Person();
  }

  @Test
  void getPersonId() {
    person.setPersonId(1L);
    assertEquals(1L,person.getPersonId());
  }

  @Test
  void getEmail() {
    String email = "kim123@gmail.com";
    person.setEmail(email);
    assertEquals(email,person.getEmail());
  }

  @Test
  void getFirstName() {
    String firstName = "Kim";
    person.setFirstName(firstName);
    assertEquals(firstName,person.getFirstName());
  }

  @Test
  void getLastName() {
    String lastName = "Etienne";
    person.setLastName(lastName);
    assertEquals(lastName,person.getLastName());
  }

  @Test
  void getPhoneNumber() {
    String phoneNumber = "123456789";
    person.setPhoneNumber(phoneNumber);
    assertEquals(phoneNumber,person.getPhoneNumber());
  }

  @Test
  void testToString() {
    person.setFirstName("Kim");
    person.setLastName("Etienne");
    person.setEmail("kim123@gmail.com");
    person.setPhoneNumber("123456789");

    String expectedString = "Person(personId=null, email=kim123@gmail.com, firstName=Kim, lastName=Etienne, phoneNumber=123456789)";
    assertEquals(expectedString, person.toString());
  }
}
