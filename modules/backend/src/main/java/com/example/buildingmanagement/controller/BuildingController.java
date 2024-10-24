package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.BuildingDTO;
import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {
  @Autowired
  private BuildingService buildingService;

  @GetMapping
  public ResponseEntity<List<BuildingDTO>> getAllBuildings() {
    List<BuildingDTO> buildings = buildingService.getAllBuildings();
    return ResponseEntity.ok(buildings);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BuildingDTO> getBuildingById(@PathVariable Long id) {
    BuildingDTO building = buildingService.getBuildingById(id);
    return building != null ? ResponseEntity.ok(building) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
    BuildingDTO createdBuilding = buildingService.saveBuilding(buildingRequestDTO);
    return ResponseEntity.ok(createdBuilding);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
    buildingService.deleteBuilding(id);
    return ResponseEntity.noContent().build();
  }
}
