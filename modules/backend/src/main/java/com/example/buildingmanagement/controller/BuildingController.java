package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.dtos.BuildingResponseDTO;
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
  @Autowired
  private BuildingRepository buildingRepository;

  @PostMapping
  public ResponseEntity<BuildingResponseDTO> createBuilding(@RequestBody BuildingRequestDTO request) {
    BuildingResponseDTO response = buildingService.createBuilding(request);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<BuildingResponseDTO>> getAllBuildings() {
    List<BuildingResponseDTO> responses = buildingService.getAllBuildings();
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BuildingResponseDTO> getBuildingById(@PathVariable Long id) {
    BuildingResponseDTO response = buildingService.getBuildingById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/latestId")
  public ResponseEntity<Long> getLatestBuildingId() {
    Long latestId = buildingRepository.findTopByOrderByBuildingIdDesc()
      .map(Building::getBuildingId)
      .orElse(0L);
    return ResponseEntity.ok(latestId);
  }


  @PutMapping("/{id}")
  public ResponseEntity<BuildingResponseDTO> updateBuilding(@PathVariable Long id, @RequestBody BuildingRequestDTO request) {
    BuildingResponseDTO response = buildingService.updateBuilding(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
    buildingService.deleteBuilding(id);
    return ResponseEntity.noContent().build();
  }

}
