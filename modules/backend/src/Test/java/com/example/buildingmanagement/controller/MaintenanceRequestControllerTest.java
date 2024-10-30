package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.service.MaintenanceService;
import com.example.buildingmanagement.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MaintenanceRequestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MaintenanceService maintenanceService;



  @BeforeEach
  void setUp() {
  }

  @Test
  void getMaintenanceRequestById() {

    MaintenanceResponseDTO maintenanceDTO = new MaintenanceResponseDTO();
    Person person = new Person();
    person.setFirstName("john");
    Room room = new Room();
    room.setNumber("1");
    maintenanceDTO.setMaintenanceRequestId(1L);
    maintenanceDTO.setPersonFirstName(person.getFirstName());
    maintenanceDTO.setRoomNum(room.getNumber());
    try {

//      when(maintenanceService.getMaintenanceRequestByMaintenanceId(1L))
//        .thenReturn(maintenanceDTO);
//
//      mockMvc.perform(get("/api/maintenance/1")
//          .accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//        .andExpect(jsonPath("$.maintenanceRequestId").value(1));
      when(maintenanceService.getMaintenanceRequestByMaintenanceId(1L)).thenReturn(maintenanceDTO);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/1"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.maintenanceRequestId").value(1));


    }  catch (Exception e) {
      e.printStackTrace();
    }



  }

  @Test
  void getByPerson() {
    MaintenanceResponseDTO maintenanceDTO = new MaintenanceResponseDTO();
    Person person = new Person();
    person.setFirstName("john");
    person.setPersonId(1l);
    Room room = new Room();
    room.setNumber("1");
    maintenanceDTO.setMaintenanceRequestId(1L);
    maintenanceDTO.setPersonFirstName(person.getFirstName());
    maintenanceDTO.setRoomNum(room.getNumber());
    try {
      when(maintenanceService.getMaintenanceRequestByPersonId(1L)).thenReturn(maintenanceDTO);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/personId"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.personId").value(1));


    }  catch (Exception e) {
      e.printStackTrace();
    }


  }

  @Test
  void getByStatus() {

    List<MaintenanceResponseDTO> maintenanceDTOList = new ArrayList<>();
    MaintenanceResponseDTO maintenanceDTO = new MaintenanceResponseDTO();
    maintenanceDTOList.add(maintenanceDTO);
    Person person = new Person();
    Room room = new Room();
    maintenanceDTO.setMaintenanceRequestId(1L);
    maintenanceDTO.setPersonFirstName(person.getFirstName());
    maintenanceDTO.setRoomNum(room.getNumber());
    maintenanceDTO.getStatus();
    try {
      when(maintenanceService.getMaintenanceRequestByStatus(StatusType.DONE)).thenReturn(maintenanceDTOList);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/status"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.status").value(StatusType.DONE));


    }  catch (Exception e) {
      e.printStackTrace();
    }



  }

  @Test
  void getByRoom() {
    MaintenanceResponseDTO maintenanceDTO = new MaintenanceResponseDTO();
    Person person = new Person();
    person.setFirstName("john");
    person.setPersonId(1l);
    Room room = new Room();
    room.setNumber("1");
    room.setRoomId(2L);
    maintenanceDTO.setMaintenanceRequestId(1L);
    maintenanceDTO.setPersonFirstName(person.getFirstName());
    maintenanceDTO.setRoomNum(room.getNumber());
    try {
      when(maintenanceService.getMaintenanceRequestByRoomId(2L)).thenReturn(maintenanceDTO);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/roomId"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.roomId").value(2));


    }  catch (Exception e) {
      e.printStackTrace();
    }


  }

  @Test
  void getAverageTimeToSolveIssue() {
    Duration averageTimeToSolve = Duration.ofHours(88);
    String expectedFormattedTime = "\"88 hours\"";

    try {
      when(maintenanceService.getAverageTimeToResolveIssue()).thenReturn(averageTimeToSolve);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/average-time"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().string(expectedFormattedTime));


    }  catch (Exception e) {
      e.printStackTrace();
    }


  }

  @Test
  void getAverageTimeToDenyIssue() {

    Duration averageTimeToSolve = Duration.ofHours(24);
    String expectedFormattedTime = "\"24 hours\"";

    try {
      when(maintenanceService.getAverageTimeToDenyIssue()).thenReturn(averageTimeToSolve);

      mockMvc.perform((RequestBuilder) get("/api/maintenance/average-time/denied"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().string(expectedFormattedTime));


    }  catch (Exception e) {
      e.printStackTrace();
    }


  }
}
