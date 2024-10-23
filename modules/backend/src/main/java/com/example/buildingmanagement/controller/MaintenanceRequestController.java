package com.example.buildingmanagement.controller;


import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.dtos.MaintenanceRequestDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenanceRequest")
@CrossOrigin
public class MaintenanceRequestController {

  @Autowired
  private MaintenanceService maintenanceService;

  @GetMapping("/{id}")
  public ResponseEntity<MaintenanceResponseDTO> getMaintenanceRequestById(@PathVariable Long maintenanceRequestId) {
    try {
      MaintenanceResponseDTO maintananceRequest = maintenanceService.getMaintenanceRequestByMaintenanceId(maintenanceRequestId);
      return ResponseEntity.ok(maintananceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);

    }


  }

  @GetMapping("{/person/id}")
  public ResponseEntity<MaintenanceResponseDTO> getByPerson(@PathVariable Long Id) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByPersonId(Id);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }

  @GetMapping("{/status}")
  public ResponseEntity<MaintenanceResponseDTO> getByStatus(@PathVariable StatusType status) {
    try {
     List<MaintenanceResponseDTO> maintenanceRequest = maintenanceService.getMaintenanceRequestByStatus(status);
      return ResponseEntity.ok((MaintenanceResponseDTO) maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }

  @GetMapping("{/room/id}")
  public ResponseEntity<MaintenanceResponseDTO> getByRoom(@PathVariable Long Id) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByRoomId(Id);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }

  @GetMapping("{/createdDate}")
  public ResponseEntity<MaintenanceResponseDTO> getByCreatedDate(@PathVariable LocalDateTime createdDate) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequest = maintenanceService.getMaintenanceRequestByCreatedDate(createdDate);
      return ResponseEntity.ok((MaintenanceResponseDTO) maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }

  @GetMapping("{/endDate}")
  public ResponseEntity<MaintenanceResponseDTO> getByEndDate(@PathVariable LocalDateTime endDate) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequest = maintenanceService.getMaintenanceRequestByEndDate(endDate);
      return ResponseEntity.ok((MaintenanceResponseDTO) maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }


}
