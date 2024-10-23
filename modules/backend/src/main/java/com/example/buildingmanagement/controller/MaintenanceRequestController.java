package com.example.buildingmanagement.controller;


import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.dtos.MaintenanceRequestDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
      MaintenanceResponseDTO maintananceRequest = maintenanceService.getMaintenanceRequestById(maintenanceRequestId);
      return ResponseEntity.ok(maintananceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);

    }


  }

  @GetMapping("/{person_id}")
  public ResponseEntity<MaintenanceResponseDTO> getPerson(@PathVariable Person Id) {
    try {
      MaintenanceResponseDTO maintenanceRequest = maintenanceService.getByPerson(Id);
      return ResponseEntity.ok(maintenanceRequest);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }

  }



}
