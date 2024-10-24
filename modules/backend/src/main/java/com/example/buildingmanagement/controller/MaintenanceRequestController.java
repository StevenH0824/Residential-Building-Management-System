package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/maintenanceRequest")
@CrossOrigin
public class MaintenanceRequestController {

  private final MaintenanceService maintenanceService;

  // Constructor-based dependency injection
  public MaintenanceRequestController(MaintenanceService maintenanceService) {
    this.maintenanceService = maintenanceService;
  }

  @GetMapping("/{maintenanceRequestId}")
  public ResponseEntity<MaintenanceResponseDTO> getMaintenanceRequestById(@PathVariable Long maintenanceRequestId) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByMaintenanceId(maintenanceRequestId);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

//  @GetMapping("/person/{personId}")
//  public ResponseEntity<MaintenanceResponseDTO> getByPerson(@PathVariable Long personId) {
//    try {
//      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByPersonId(personId);
//      return ResponseEntity.ok(maintenanceRequest);
//    } catch (ResponseStatusException ex) {
//      return ResponseEntity.status(ex.getStatusCode()).body(null);
//    }
//  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<MaintenanceResponseDTO>> getByStatus(@PathVariable StatusType status) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequests = maintenanceService.getMaintenanceRequestByStatus(status);
      return ResponseEntity.ok(maintenanceRequests);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

//  @GetMapping("/room/{roomId}")
//  public ResponseEntity<MaintenanceResponseDTO> getByRoom(@PathVariable Long roomId) {
//    try {
//      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByRoomId(roomId);
//      return ResponseEntity.ok(maintenanceRequest);
//    } catch (ResponseStatusException ex) {
//      return ResponseEntity.status(ex.getStatusCode()).body(null);
//    }
//  }

  @GetMapping("/createdDate/{createdDate}")
  public ResponseEntity<List<MaintenanceResponseDTO>> getByCreatedDate(@PathVariable LocalDateTime createdDate) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequests = maintenanceService.getMaintenanceRequestByCreatedDate(createdDate);
      return ResponseEntity.ok(maintenanceRequests);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/endDate/{endDate}")
  public ResponseEntity<List<MaintenanceResponseDTO>> getByEndDate(@PathVariable LocalDateTime endDate) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequests = maintenanceService.getMaintenanceRequestByEndDate(endDate);
      return ResponseEntity.ok(maintenanceRequests);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }
}
