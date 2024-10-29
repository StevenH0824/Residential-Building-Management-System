package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.MaintenanceRequestRepository;
import com.example.buildingmanagement.repository.PersonRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {


  @InjectMocks
  private MaintenanceService maintenanceService;

  @Mock
  private MaintenanceRequestRepository maintenanceRequestRepository;

//  @Mock
//  private MaintenanceResponseDTO maintenanceResponseDTO;

  @Mock
  private PersonRepository personRepository;

  @Mock
  private RoomRepository roomRepository;


//  @BeforeEach
//  void setUp() {
//    MockitoAnnotations.openMocks(this);
//  }

  @Test
  void getMaintenanceRequestByPersonId() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    Person person = new Person();
    person.setPersonId(2L);
    maintenanceRequest.setPerson(person);
    maintenanceRequest.setMaintenanceRequestId(1L);
    when(personRepository.findByPersonId(2L)).thenReturn(person);
    when(maintenanceRequestRepository.findByPerson(person)).thenReturn(maintenanceRequest);
    ModelMapper modelMapper = new ModelMapper();
    MaintenanceService maintenanceService = new MaintenanceService(
      maintenanceRequestRepository, personRepository, roomRepository, modelMapper
    );
    MaintenanceResponseDTO result = maintenanceService.getMaintenanceRequestByPersonId(person.getPersonId());
    assertEquals(1L, result.getMaintenanceRequestId());

  }

  @Test
  void getMaintenanceRequestByMaintenanceId() {
    //Arrange
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    Person  person = new Person();
    Room room = new Room();
    maintenanceRequest.setMaintenanceRequestId(1L);
    maintenanceRequest.setPerson(person);
    maintenanceRequest.setRoom(room);



    when(maintenanceRequestRepository.findByMaintenanceRequestId(1L)).thenReturn(maintenanceRequest);

    MaintenanceResponseDTO result = maintenanceService.getMaintenanceRequestByMaintenanceId(maintenanceRequest.getMaintenanceRequestId());

    assertEquals(1L, result.getMaintenanceRequestId());
  }

  @Test
  void getMaintenanceRequestByStatus() {
    List<MaintenanceRequest> maintenanceRequestList = new ArrayList<>();
    Person person = new Person();
    Room room = new Room();
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setStatus(StatusType.APPROVED);
    maintenanceRequest.setRoom(room);
    maintenanceRequest.setPerson(person);
    maintenanceRequestList.add(maintenanceRequest);
    MaintenanceRequest maintenanceRequest1 = new MaintenanceRequest();
    maintenanceRequest1.setStatus(StatusType.APPROVED);
    maintenanceRequest1.setRoom(room);
    maintenanceRequest1.setPerson(person);
    maintenanceRequestList.add(maintenanceRequest1);
    //maintenanceRequestRepository.save(maintenanceRequest);
    when(maintenanceRequestRepository.findByStatus(StatusType.APPROVED)).thenReturn(maintenanceRequestList);
    List<MaintenanceResponseDTO> result = maintenanceService.getMaintenanceRequestByStatus(StatusType.APPROVED);
    assertEquals(2, result.size());

  }

  @Test
  void getMaintenanceRequestByRoomId() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    Room room = new Room();
    room.setRoomId(1L);
    maintenanceRequest.setRoom(room);
    maintenanceRequest.setMaintenanceRequestId(1L);
    when(roomRepository.findByRoomId(1L)).thenReturn(room);
    when(maintenanceRequestRepository.findByRoom(room)).thenReturn(maintenanceRequest);
    ModelMapper modelMapper = new ModelMapper();
    MaintenanceService maintenanceService = new MaintenanceService(
      maintenanceRequestRepository, personRepository, roomRepository, modelMapper
    );
    MaintenanceResponseDTO result = maintenanceService.getMaintenanceRequestByRoomId(1L);
    assertEquals(1L, result.getMaintenanceRequestId());

  }
}
