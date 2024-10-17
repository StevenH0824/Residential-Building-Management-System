package com.example.buildingmanagement.Services;

import com.example.buildingmanagement.Repositories.PersonRepository;
import com.example.buildingmanagement.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServices {

    @Autowired
    private PersonRepository personRepository;

    public Person getPersonById(Long personId) {
      return personRepository.findByPersonId(personId);
    }
    public Optional<Person> getPersonByFirstName(String firstName) {
      return personRepository.findByFirstName(firstName);
    }
    public Optional<Person> getPersonByLastName(String lastName) {
      return personRepository.findByLastName(lastName);
    }
    public Optional<Person> getPersonByPhoneNumber (String phoneNumber) {
      return personRepository.findByPhoneNumber(phoneNumber);
    }
    public List<Person> getAllPersons() {
      return personRepository.findAll();
    }
    public Person savePerson(Person person) {
      return personRepository.save(person);
    }
    public void deletePerson(Long personId) {
      personRepository.deleteById(personId);
    }
  }
