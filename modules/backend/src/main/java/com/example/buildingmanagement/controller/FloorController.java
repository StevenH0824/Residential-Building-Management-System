package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.FloorDTO;
import com.example.buildingmanagement.service.FloorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/floors")
@RestController
public class FloorController {

  @Autowired
  private FloorService floorService;

  // Get all floors
  @GetMapping
  public List<FloorDTO> getAllFloors() {
    return floorService.getAllFloors();
  }

  // Get floor by ID
  @GetMapping("/{id}")
  public ResponseEntity<FloorDTO> getFloorById(@PathVariable Long id) {
    FloorDTO floor = floorService.getFloorById(id);
    return floor != null ? ResponseEntity.ok(floor) : ResponseEntity.notFound().build();
  }

  // Create or update a floor
  @PostMapping
  public ResponseEntity<FloorDTO> createFloor(@RequestBody FloorDTO floorDTO) {
    FloorDTO savedFloor = floorService.saveFloor(floorDTO);
    return ResponseEntity.ok(savedFloor);
  }

  // Update a floor
  @PutMapping("/{id}")
  public ResponseEntity<FloorDTO> updateFloor(@PathVariable Long id, @RequestBody FloorDTO floorDTO) {
    FloorDTO existingFloor = floorService.getFloorById(id);
    if (existingFloor == null) {
      return ResponseEntity.notFound().build();
    }
    floorDTO.setFloorId(id); // Ensure the DTO has the correct ID for update
    FloorDTO updatedFloor = floorService.saveFloor(floorDTO);
    return ResponseEntity.ok(updatedFloor);
  }

  // Delete a floor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
    floorService.deleteFloor(id);
    return ResponseEntity.noContent().build();
  }
}
