package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.FloorRequestDTO;
import com.example.buildingmanagement.dtos.FloorResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.service.FloorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/floors")
@RestController
public class FloorController {

  @Autowired
  private FloorService floorService;
  private static final Logger logger = LoggerFactory.getLogger(FloorService.class);

  // Get all floors
  @GetMapping("/all")
  public List<FloorRequestDTO> getAllFloors() {
    return floorService.getAllFloors();
  }

  // Get all floors by building id
  @GetMapping("/by-building")
  public List<FloorResponseDTO> getFloors(@RequestParam Long buildingId) {
    if (buildingId == null || buildingId <= 0) {
      logger.error("Invalid building ID: {}", buildingId);
      throw new IllegalArgumentException("Building ID must be a positive number");
    }
    logger.info("Fetching floors for building ID: {}", buildingId);
    return floorService.getFloorsByBuildingId(buildingId);
  }

  // Get floor by ID
  @GetMapping("/{id}")
  public ResponseEntity<FloorRequestDTO> getFloorById(@PathVariable Long id) {
    FloorRequestDTO floor = floorService.getFloorById(id);
    return floor != null ? ResponseEntity.ok(floor) : ResponseEntity.notFound().build();
  }

  // Create or update a floor
  @PostMapping
  public ResponseEntity<FloorRequestDTO> createFloor(@RequestBody FloorRequestDTO floorRequestDTO) {
    FloorRequestDTO savedFloor = floorService.saveFloor(floorRequestDTO);
    return ResponseEntity.ok(savedFloor);
  }

  // Update a floor
  @PutMapping("/{id}")
  public ResponseEntity<FloorRequestDTO> updateFloor(@PathVariable Long id, @RequestBody FloorRequestDTO floorRequestDTO) {
    FloorRequestDTO existingFloor = floorService.getFloorById(id);
    if (existingFloor == null) {
      return ResponseEntity.notFound().build();
    }
    floorRequestDTO.setFloorId(id); // Ensure the DTO has the correct ID for update
    FloorRequestDTO updatedFloor = floorService.saveFloor(floorRequestDTO);
    return ResponseEntity.ok(updatedFloor);
  }

  // Delete a floor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
    floorService.deleteFloor(id);
    return ResponseEntity.noContent().build();
  }
}
