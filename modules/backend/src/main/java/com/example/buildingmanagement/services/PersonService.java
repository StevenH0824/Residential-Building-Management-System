package com.example.buildingmanagement.services;


import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

//
//
//  public List<Person> getAllPersons() {
//    return personRepository.findAll();
//  }
//
//  public Optional<Person> getPersonById(Long id) {
//    return personRepository.findById(id);
//  }
//
//  public Person createPerson(Person person) {
//    return personRepository.save(person);
//  }
//
//  public Person updatePerson(Long id, Person updatedPerson) {
//    Optional<Person> person = personRepository.findById(id);
//    if (person.isPresent()) {
//      Person existingPerson = person.get();
//      existingPerson.setEmail(updatedPerson.getEmail());
//      existingPerson.setFirstName(updatedPerson.getFirstName());
//      existingPerson.setLastName(updatedPerson.getLastName());
//      existingPerson.setPhoneNumber(updatedPerson.getPhoneNumber());
//      return personRepository.save(existingPerson);
//    } else {
//      throw new RuntimeException("Person not found with id " + id);
//    }
//  }
////  public String getPersonByFirstName(Long id) {
////    return personRepository.findById(id)
////      .map(Person::getFirstName)
////      .orElseThrow(() -> new RuntimeException("Person not found with id " + id));
////  }
//
//  public String getPersonByLastName(Long id) {
//    return personRepository.findById(id)
//      .map(Person::getLastName)
//      .orElseThrow(() -> new RuntimeException("Person not found with id " + id));
//  }
//
//  public String getPersonByPhoneNumber(Long id) {
//    return personRepository.findById(id)
//      .map(Person::getPhoneNumber)
//      .orElseThrow(() -> new RuntimeException("Person not found with id " + id));
//  }
//
//  public void deletePerson(Long id) {
//    personRepository.deleteById(id);
//  }
  }
