package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.ControlGroupPersonRequestDTO;
import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.entities.ControlGroupPerson;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.ControlGroupPersonRepository;
import com.example.buildingmanagement.repository.ControlGroupRepository;
import com.example.buildingmanagement.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ControlGroupPersonServiceTest {

  // Mock all the repos i will be making use of
  @Mock
  private ControlGroupPersonRepository controlGroupPersonRepository;

  @Mock
  private ControlGroupRepository controlGroupRepository;

  @Mock
  private PersonRepository personRepository;

  @InjectMocks //Use this on the class being tested
  private ControlGroupPersonService controlGroupPersonService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void createControlGroupPerson() {
      // Arrange: Prepare the DTO and mock repository response
      ControlGroupPersonRequestDTO controlGroupPersonRequestDTO = new ControlGroupPersonRequestDTO();
      controlGroupPersonRequestDTO.setControlGroupId(9l);
      controlGroupPersonRequestDTO.setPersonId(10L);
      controlGroupPersonRequestDTO.setExpirationDate(LocalDate.now());
      controlGroupPersonRequestDTO.setStartDate(LocalDate.now());

      ControlGroup controlGroup = new ControlGroup();
      controlGroup.setControlGroupId(9L);
      Person person = new Person();
      person.setPersonId(10L);

      when(controlGroupRepository.getReferenceById(9L)).thenReturn(controlGroup);
      when(personRepository.getReferenceById(10L)).thenReturn(person);
      when(controlGroupPersonRepository.save(any())).thenReturn(new ControlGroupPerson(9L, controlGroup, person, LocalDate.now(),LocalDate.now()));


      ControlGroupPerson result = controlGroupPersonService.createControlGroupPerson(controlGroupPersonRequestDTO);

      assertNotNull(result);
      assertEquals(9L, result.getControlGroup().getControlGroupId());
      assertEquals(10L, result.getPerson().getPersonId());
      assertEquals(LocalDate.now(), result.getStart_date());
      assertEquals(LocalDate.now(), result.getExpiration_date());

      //Don't have to verify but it is good to do it.
      verify(controlGroupRepository, times(1)).getReferenceById(9L);
      verify(personRepository, times(1)).getReferenceById(10L);
      verify(controlGroupPersonRepository).save(any(ControlGroupPerson.class));

    }
  }

