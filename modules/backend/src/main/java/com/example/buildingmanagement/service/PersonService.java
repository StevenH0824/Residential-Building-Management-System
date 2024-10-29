package com.example.buildingmanagement.service;


import com.example.buildingmanagement.dtos.PersonDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
  private final PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository){
    this.personRepository = personRepository;
  }
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

  public Optional<Person> updatePerson(Long id, PersonDTO request) {
    // Check if the person exists
    Optional<Person> existingPerson = personRepository.findById(id);

    if (existingPerson.isPresent()) {
      // Update fields
      Person personToUpdate = existingPerson.get();
      personToUpdate.setFirstName(request.getFirstName());
      personToUpdate.setLastName(request.getLastName());
      personToUpdate.setEmail(request.getEmail());
      personToUpdate.setPhoneNumber(request.getPhoneNumber());

      // Save the updated person back to the repository
      return Optional.of(personRepository.save(personToUpdate));
    }

    // Return empty if person not found
    return Optional.empty();
  }

  public void deletePerson(Long id) {
    personRepository.deleteById(id);
  }

  public Optional<Person> updatePerson(Long id, PersonDTO request) {
    // Check if the person exists
    Optional<Person> existingPerson = personRepository.findById(id);

    if (existingPerson.isPresent()) {
      // Update fields
      Person personToUpdate = existingPerson.get();
      personToUpdate.setFirstName(request.getFirstName());
      personToUpdate.setLastName(request.getLastName());
      personToUpdate.setEmail(request.getEmail());
      personToUpdate.setPhoneNumber(request.getPhoneNumber());

      // Save the updated person back to the repository
      return Optional.of(personRepository.save(personToUpdate));
    }

    // Return empty if person not found
    return Optional.empty();
  }
  }
