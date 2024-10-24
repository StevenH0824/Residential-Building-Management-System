package com.example.buildingmanagement.service;


import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public Optional<Person> getPersonById(Long id) {
    return personRepository.findById(id);
  }


  public List<Person> getPersonByPhoneNumber(String phoneNumber) {
    return personRepository.findByPhoneNumber(phoneNumber);
  }

  public List<Person> getPersonsByFirstName(String firstName) {
    return personRepository.findByFirstName(firstName);
  }

  public List<Person> getPersonsByLastName(String lastName) {
    return personRepository.findByLastName(lastName);
  }


  public List<Person> getPersonsByEmail(String email) {
    return personRepository.findByEmail(email);
  }

  public Person createPerson(Person person) {
    return personRepository.save(person);
  }

  public Optional<Person> updatePerson(Long id) { return personRepository.findById(id);}

  public void deletePerson(Long id) {
    personRepository.deleteById(id);
  }
  }
