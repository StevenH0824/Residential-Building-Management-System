package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping
  public List <Person> getAllPersons() {
    List <Person> person = personService.getAllPersons();
    return person;
  }
  @GetMapping("/{id}")
  public Optional<Person> getPersonByID(@PathVariable Long id) {
    Optional<Person> person = personService.getPersonById(id);
    return person;
  }
  @GetMapping("/searchnum/{phoneNumber}")
  public List<Person> getPersonByPhoneNumber (@PathVariable String phoneNumber) {
    List <Person> person = personService.getPersonByPhoneNumber(phoneNumber);
    return person;
  }
  @GetMapping("/searchfn/{firstName}")
  public List<Person> getPersonByFirstName (@PathVariable String firstName) {
    List <Person> person = personService.getPersonsByFirstName(firstName);
    return person;
  }
  @GetMapping("/searchln/{lastName}")
  public List<Person> getPersonByLastName (@PathVariable String lastName) {
    List <Person> person = personService.getPersonsByLastName(lastName);
    return person;
  }
  @GetMapping("/searchem/{email}")
  public List<Person> getPersonByEmail (@PathVariable String email) {
    List <Person> person = personService.getPersonsByEmail(email);
    return person;
  }

  @DeleteMapping("/delid/{id}")
  public void deletePerson(@PathVariable Long id) {
    personService.deletePerson(id);
  }
  @PostMapping
  public Person createPerson(@RequestBody Person person){
    Person personEntity = personService.createPerson(person);
    return personEntity;
  }
}



