package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.MaintenanceRequestDTO;
import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.service.MaintenanceService;
import com.example.buildingmanagement.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
@CrossOrigin
public class MaintenanceRequestController {

  private final MaintenanceService maintenanceService;
  private final PersonService personService;


  public MaintenanceRequestController(MaintenanceService maintenanceService, PersonService personService) {
    this.maintenanceService = maintenanceService;
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<MaintenanceResponseDTO>> getAllMaintenanceRequests() {
    try {
      List<MaintenanceResponseDTO> maintenanceRequests = maintenanceService.getAllMaintenanceRequests();
      return ResponseEntity.ok(maintenanceRequests);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
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

  @GetMapping("/person/{personId}")
  public ResponseEntity<MaintenanceResponseDTO> getByPerson(@PathVariable Long personId) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByPersonId(personId);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<MaintenanceResponseDTO>> getByStatus(@PathVariable StatusType status) {
    try {
      List<MaintenanceResponseDTO> maintenanceRequests = maintenanceService.getMaintenanceRequestByStatus(status);
      return ResponseEntity.ok(maintenanceRequests);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/room/{roomId}")
  public ResponseEntity<MaintenanceResponseDTO> getByRoom(@PathVariable Long roomId) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getMaintenanceRequestByRoomId(roomId);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/average-time")
  public ResponseEntity<String> getAverageTimeToSolveIssue() {
    Duration averageTime = maintenanceService.getAverageTimeToResolveIssue();
    String formattedTime = String.format("\"%d hours\"", averageTime.toHours());
    return new ResponseEntity<>(formattedTime, HttpStatus.OK);
  } //duration serializes object into iso-8601 so pt24h is average time 24 hours and formatted time format into string.

  @GetMapping("/average-time/denied")
  public ResponseEntity<String> getAverageTimeToDenyIssue() {
    Duration averageTime = maintenanceService.getAverageTimeToDenyIssue();
    String formattedTime = String.format("\"%d hours\"", averageTime.toHours());
    return new ResponseEntity<>(formattedTime, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MaintenanceRequest> createMaintenanceRequest(@RequestBody MaintenanceRequestDTO dto) {
    try {
      MaintenanceRequest createdRequest = maintenanceService.createMaintenanceRequest(dto);
      return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }
}
