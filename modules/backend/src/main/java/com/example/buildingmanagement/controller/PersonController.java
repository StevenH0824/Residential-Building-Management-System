//package com.example.buildingmanagement.controller;
//
//import com.example.buildingmanagement.dtos.PersonDTO;
//import com.example.buildingmanagement.entities.Person;
//import com.example.buildingmanagement.services.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/persons")
//public class PersonController {
//
//  @Autowired
//  private PersonService personService;
//
//  // Get a person by ID
//  @GetMapping("/{id}")
//  public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long personId) {
//    return PersonService.getPersonById(personId)
//      .map(ResponseEntity::ok)
//      .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//
//  // Get a person by first name
//  @GetMapping("/first-name/{firstName}")
//  public ResponseEntity<PersonDTO> findPersonByFirstName(@PathVariable("firstName") String firstName) {
//    return PersonService.getPersonByFirstName(firstName)
//      .map(ResponseEntity::ok)
//      .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//  // Get a person by last name
//  @GetMapping("/last-name/{lastName}")
//  public ResponseEntity<PersonDTO> getPersonByLastName(@PathVariable("lastName") String lastName) {
//    return PersonService.getPersonByLastName(lastName)
//      .map(ResponseEntity::ok)
//      .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//  // Get a person by phone number
//  @GetMapping("/phone/{phoneNumber}")
//  public ResponseEntity<PersonDTO> getPersonByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
//    return PersonService.getPersonByPhoneNumber(phoneNumber)
//      .map(ResponseEntity::ok)
//      .orElseGet(() -> ResponseEntity.notFound().build());
//  }
//  // Get all persons
//  @GetMapping
//  public ResponseEntity<List<PersonDTO>> getAllPersons() {
//    List<PersonDTO> personDTOs = PersonService.getAllPersons();
//    return ResponseEntity.ok(personDTOs);
//  }
//  // Create a new person
//  @PostMapping
//  public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
//    PersonDTO savedPersonDTO = PersonService.savePerson(personDTO);
//    return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonDTO);
//  }
//  // Delete a person
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deletePerson(@PathVariable("id") Long personId) {
//    PersonService.deletePerson(personId);
//    return ResponseEntity.noContent().build();
//
//  }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
