package com.example.buildingmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.PersonRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;


  private Person person;
  private Person person1;
  private Person person2;

  @BeforeEach
  public void setUp() {
     person1 = new Person(1L, "kim123@gmail.com", "Kim", "Etienne", "123456789");
     person2 = new Person(2L, "steven123@gmail.com", "Steven", "Hernandez", "987654321");
     person = new Person(3L, "danaAndres@gmail.com", "Dana", "Andres", "135792468");
  }

  @Test
  public void testGetAllPersons() {
    List<Person> persons = Arrays.asList(person1, person2);
    when(personRepository.findAll()).thenReturn(persons);
    List<Person> result = personService.getAllPersons();
    assertEquals(2, result.size());
    assertEquals("Kim", result.get(0).getFirstName());
    assertEquals("Steven", result.get(1).getFirstName());
    verify(personRepository, times(1)).findAll();
  }
  @Test
  public void testGetPersonById() {
    when(personRepository.findById(3L)).thenReturn(Optional.of(person));
    Optional<Person> result = personService.getPersonById(3L);
    assertTrue(result.isPresent());
    assertEquals("Dana", result.get().getFirstName());
    verify(personRepository, times(1)).findById(3L);
  }
  @Test
  public void testGetPersonByPhoneNumber() {
    when(personRepository.findByPhoneNumber("135792468")).thenReturn(Arrays.asList(person));
    List<Person> result = personService.getPersonByPhoneNumber("135792468");
    assertEquals(1, result.size());
    assertEquals("Dana", result.get(0).getFirstName());
    verify(personRepository, times(1)).findByPhoneNumber("135792468");
  }
  @Test
  public void testGetPersonsByFirstName() {
    List<Person> persons = Arrays.asList(person1, person2);
    when(personRepository.findByFirstName("Kim")).thenReturn(persons);
    List<Person> result = personService.getPersonsByFirstName("Kim");
    assertEquals("Etienne", result.get(0).getLastName());
    verify(personRepository, times(1)).findByFirstName("Kim");
  }
  @Test
  public void testGetPersonsByLastName() {
    List<Person> persons = Arrays.asList(person1, person2);
    when(personRepository.findByLastName("Etienne")).thenReturn(persons);
    List<Person> result = personService.getPersonsByLastName("Etienne");
    assertEquals("Kim", result.get(0).getFirstName());
    verify(personRepository, times(1)).findByLastName("Etienne");
  }
  @Test
  public void testGetPersonsByEmail() {
    when(personRepository.findByEmail("kim123@gmail.com")).thenReturn(Arrays.asList(person1));
    List<Person> result = personService.getPersonsByEmail("kim123@gmail.com");
    assertEquals(1, result.size());
    assertEquals("Kim", result.get(0).getFirstName());
    verify(personRepository, times(1)).findByEmail("kim123@gmail.com");
  }
  @Test
  public void testCreatePerson() {
    when(personRepository.save(person)).thenReturn(new Person(1L, "john@example.com", "John", "Doe", "123456789"));

    Person createdPerson = personService.createPerson(person);

    assertNotNull(createdPerson.getPersonId());
    assertEquals("John", createdPerson.getFirstName());
    verify(personRepository, times(1)).save(person);
  }

  @Test
  public void testDeletePerson() {
    Long id = 1L;
    personService.deletePerson(id);
    verify(personRepository, times(1)).deleteById(id);
  }
}


