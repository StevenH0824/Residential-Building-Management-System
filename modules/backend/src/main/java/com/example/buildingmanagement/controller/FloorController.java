package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.BuildingDTO;
import com.example.buildingmanagement.dtos.FloorRequestDTO;
import com.example.buildingmanagement.dtos.FloorResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.service.FloorService;
import com.example.buildingmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/floors")
@RestController
public class FloorController {

  @Autowired
  private FloorService floorService;

  @Autowired
  private RoomService roomService; // Autowired for RoomService

  // Get all floors
  @GetMapping("/all")
  public List<FloorResponseDTO> getAllFloors() {
    return floorService.getAllFloors();
  }

  // Get all floors by building ID
  @GetMapping("/by-building")
  public List<FloorResponseDTO> getFloors(@RequestParam Long buildingId) {
    if (buildingId == null || buildingId <= 0) {
      throw new IllegalArgumentException("Building ID must be a positive number");
    }
    return floorService.getFloorsByBuildingId(buildingId);
  }

  // Get floor by ID
  @GetMapping("/{id}")
  public ResponseEntity<FloorResponseDTO> getFloor(@PathVariable Long id) {
    return floorService.getFloorById(id);
  }

  // Create a new floor
  @PostMapping("/floors")
  public ResponseEntity<FloorResponseDTO> createFloor(@RequestBody FloorRequestDTO floorRequestDTO) {
    Floor savedFloor = floorService.saveFloor(floorRequestDTO);

    FloorResponseDTO responseDTO = new FloorResponseDTO(
      savedFloor.getFloorId(),
      savedFloor.getNumber(),
      savedFloor.getDescription(),
      new BuildingDTO(
        savedFloor.getBuilding().getBuildingId(),
        savedFloor.getBuilding().getName(),
        savedFloor.getBuilding().getAddress()),
      null,
      null
    );

    return ResponseEntity.ok(responseDTO);
  }

  // Update a floor
  @PutMapping("/{id}")
  public ResponseEntity<FloorResponseDTO> updateFloor(@PathVariable Long id, @RequestBody FloorRequestDTO floorRequestDTO) {
    floorRequestDTO.setFloorId(id); // Set the floor ID for the update
    Floor updatedFloor = floorService.saveFloor(floorRequestDTO); // Save the updated floor

    FloorResponseDTO responseDTO = new FloorResponseDTO(
      updatedFloor.getFloorId(),
      updatedFloor.getNumber(),
      updatedFloor.getDescription(),
      new BuildingDTO(
        updatedFloor.getBuilding().getBuildingId(),
        updatedFloor.getBuilding().getName(),
        updatedFloor.getBuilding().getAddress()),
      null,
      null
    );

    return ResponseEntity.ok(responseDTO);
  }

  // Delete a floor
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
    floorService.deleteFloor(id);
    return ResponseEntity.noContent().build();
  }
}
