package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Floor;
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

  @GetMapping
  public List<Floor> getAllFloors() {
    return floorService.getAllFloors();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Floor> getFloorById(@PathVariable Long id) {
    Floor floor = floorService.getFloorById(id);
    return floor != null ? ResponseEntity.ok(floor) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public Floor createFloor(@RequestBody Floor floor) {
    return floorService.saveFloor(floor);
  }

  @DeleteMapping("/{id}")
  public void deleteFloor(@PathVariable Long id) {
    floorService.deleteFloor(id);
  }
}
