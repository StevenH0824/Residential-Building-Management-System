package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.PersonDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private PersonService personService;

  @InjectMocks
  private PersonController personController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
  }

  @Test
  void testGetAllPersons() throws Exception {
    Person person1 = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");
    Person person2 = new Person(2L, "jane.doe@example.com", "Jane", "Doe", "654321");

    when(personService.getAllPersons()).thenReturn(Arrays.asList(person1, person2));

    mockMvc.perform(get("/api/persons"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].personId").value(1))
      .andExpect(jsonPath("$[0].firstName").value("John"))
      .andExpect(jsonPath("$[0].lastName").value("Doe"))
      .andExpect(jsonPath("$[1].personId").value(2))
      .andExpect(jsonPath("$[1].firstName").value("Jane"))
      .andExpect(jsonPath("$[1].lastName").value("Doe"));

    verify(personService, times(1)).getAllPersons();
  }

  @Test
  void testGetPersonById() throws Exception {
     Person person = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.getPersonById(1L)).thenReturn(Optional.of(person));

    mockMvc.perform(get("/api/persons/{id}", 1L))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.personId").value(1))
      .andExpect(jsonPath("$.firstName").value("John"))
      .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(personService, times(1)).getPersonById(1L);
  }

  @Test
  void testGetPersonById_NotFound() throws Exception {
    when(personService.getPersonById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/persons/{id}", 1L))
      .andExpect(status().isOk());

    verify(personService, times(1)).getPersonById(1L);
  }

  @Test
  void testCreatePerson() throws Exception {
    Person newPerson = new Person(null, "john.doe@example.com", "John", "Doe", "123456");
    Person createdPerson = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.createPerson(any(Person.class))).thenReturn(createdPerson);

    mockMvc.perform(post("/api/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"123456\"}"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.personId").value(1))
      .andExpect(jsonPath("$.firstName").value("John"))
      .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(personService, times(1)).createPerson(any(Person.class));
  }

  @Test
  void testUpdatePerson() throws Exception {
    PersonDTO personDTO = new PersonDTO(1L,"john.doe@example.com", "John", "Doe", "123456");
    Person updatedPerson = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.updatePerson(eq(1L), any(PersonDTO.class))).thenReturn(Optional.of(updatedPerson));

    mockMvc.perform(put("/api/persons/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"123456\"}"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.personId").value(1))
      .andExpect(jsonPath("$.firstName").value("John"))
      .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(personService, times(1)).updatePerson(eq(1L), any(PersonDTO.class));
  }

  @Test
  void testUpdatePerson_NotFound() throws Exception {
    PersonDTO personDTO = new PersonDTO(1L,"john.doe@example.com", "John", "Doe", "123456");

    when(personService.updatePerson(eq(2L), any(PersonDTO.class))).thenReturn(Optional.empty());

    mockMvc.perform(put("/api/persons/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"phone\":\"123456\"}"))
      .andExpect(status().isNotFound());

    verify(personService, times(1)).updatePerson(eq(1L), any(PersonDTO.class));
  }

  @Test
  void testDeletePerson() throws Exception {
    doNothing().when(personService).deletePerson(1L);

    mockMvc.perform(delete("/api/persons/{id}", 1L))
      .andExpect(status().isOk());
      //.andExpect(status().isNoContent());

    verify(personService, times(1)).deletePerson(1L);
  }

  @Test
  void testGetPersonByPhoneNumber() throws Exception {
    Person person = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.getPersonByPhoneNumber("123456")).thenReturn(Arrays.asList(person));

    mockMvc.perform(get("/api/persons/searchnum/{phoneNumber}", "123456"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].personId").value(1))
      .andExpect(jsonPath("$[0].firstName").value("John"))
      .andExpect(jsonPath("$[0].lastName").value("Doe"));

    verify(personService, times(1)).getPersonByPhoneNumber("123456");
  }

  @Test
  void testGetPersonByFirstName() throws Exception {
    Person person = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.getPersonsByFirstName("John")).thenReturn(Arrays.asList(person));

    mockMvc.perform(get("/api/persons/searchfn/{firstName}", "John"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].personId").value(1))
      .andExpect(jsonPath("$[0].firstName").value("John"))
      .andExpect(jsonPath("$[0].lastName").value("Doe"));

    verify(personService, times(1)).getPersonsByFirstName("John");
  }

  @Test
  void testGetPersonByLastName() throws Exception {
    Person person = new Person(1L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.getPersonsByLastName("Doe")).thenReturn(Arrays.asList(person));

    mockMvc.perform(get("/api/persons/searchln/{lastName}", "Doe"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].personId").value(1))
      .andExpect(jsonPath("$[0].firstName").value("John"))
      .andExpect(jsonPath("$[0].lastName").value("Doe"));

    verify(personService, times(1)).getPersonsByLastName("Doe");
  }

  @Test
  void testGetPersonByEmail() throws Exception {
    Person person = new Person(10L, "john.doe@example.com", "John", "Doe", "123456");

    when(personService.getPersonsByEmail("john.doe@example.com")).thenReturn(Arrays.asList(person));

    mockMvc.perform(get("/api/persons/searchem/{email}", "john.doe@example.com"))
      .andExpect(status().isOk());


  }

}
