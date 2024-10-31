package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.PersonDTO;
import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.service.ControlGroupPersonService;
import com.example.buildingmanagement.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
//@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private ControlGroupPersonService controlGroupPersonService;


  @GetMapping
  public List <Person> getAllPersons() {
    List <Person> person = personService.getAllPersons();
    return person;
  }
  @GetMapping("/by-person")
  public Optional<Person> getPersonByID(@RequestParam Long personId) {
    Optional<Person> person = personService.getPersonById(personId);
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

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable Long id) {
    personService.deletePerson(id);
  }

  @PostMapping
  public Person createPerson(@RequestBody Person person){
    Person personEntity = personService.createPerson(person);
    return personEntity;
  }
  @GetMapping("/{id}/spaces")
  public ResponseEntity<List<RoomResponseDTO>> getAccessibleRooms(@PathVariable Long id) {
    try {
      List<RoomResponseDTO> rooms = controlGroupPersonService.getAccessibleRoomsByPersonId(id);
      return ResponseEntity.ok(rooms);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonDTO request) {
    Optional<Person> updatedPerson = personService.updatePerson(id, request);
    if (updatedPerson.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedPerson.get());
  }
}



