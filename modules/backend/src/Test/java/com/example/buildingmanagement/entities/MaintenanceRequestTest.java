package com.example.buildingmanagement.entities;

import com.example.buildingmanagement.enums.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRequestTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void getMaintenanceRequestId() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setMaintenanceRequestId(1234L);
    assertEquals(maintenanceRequest.getMaintenanceRequestId(),1234L);

  }

  @Test
  void getCreatedDate() {
    LocalDateTime createdDate = LocalDateTime.of(2019, 3, 28, 14, 33, 48, 640000);
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setCreatedDate(createdDate);
    assertEquals(maintenanceRequest.getCreatedDate(), createdDate);


  }

  @Test
  void getEndDate() {
    LocalDateTime endDate = LocalDateTime.of(2019, 3, 28, 14, 33, 48, 640000);
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setEndDate(endDate);
    assertEquals(maintenanceRequest.getEndDate(), endDate);

  }

  @Test
  void getIssue() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setIssue("test");
    assertEquals(maintenanceRequest.getIssue(), "test");

  }

  @Test
  void getStatus() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setStatus(StatusType.DENIED);
    assertEquals(maintenanceRequest.getStatus(),StatusType.DENIED);
    MaintenanceRequest maintenanceRequest2 = new MaintenanceRequest();
    maintenanceRequest2.setStatus(StatusType.APPROVED);
    assertEquals(maintenanceRequest2.getStatus(),StatusType.APPROVED);
    MaintenanceRequest maintenanceRequest3 = new MaintenanceRequest();
    maintenanceRequest3.setStatus(StatusType.PENDING);
    assertEquals(maintenanceRequest3.getStatus(),StatusType.PENDING);


  }

  @Test
  void getPerson() {
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    Person person = new Person();
    maintenanceRequest.setPerson(person);
    assertEquals(maintenanceRequest.getPerson(), person);


  }

  @Test
  void getRoom() {
    Room room = new Room();
    MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
    maintenanceRequest.setRoom(room);
    assertEquals(maintenanceRequest.getRoom(), room);


  }


}
