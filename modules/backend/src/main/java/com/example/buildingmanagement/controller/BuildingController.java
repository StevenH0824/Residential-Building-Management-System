package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.service.BuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/buildings")
@RestController
public class BuildingController {
  @Autowired
  private BuildingService buildingService;


  @GetMapping
  public ResponseEntity<List<Building>> getAllBuildings() {
    List<Building> buildings = buildingService.getAllBuildings();
    return ResponseEntity.ok(buildings);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
    Building building = buildingService.getBuildingById(id);
    return building != null ? ResponseEntity.ok(building) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public Building createBuilding(@RequestBody Building building) {
    return buildingService.saveBuilding(building);
  }

  @DeleteMapping("/{id}")
  public void deleteBuilding(@PathVariable Long id) {
    buildingService.deleteBuilding(id);
  }
}
