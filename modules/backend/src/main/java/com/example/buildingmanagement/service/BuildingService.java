package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.repository.BuildingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
  @Autowired
  private BuildingRepository buildingRepository;

  public List<Building> getAllBuildings() {
    return buildingRepository.findAll();
  }

  public Building getBuildingById(Long id) {
    return buildingRepository.findById(id).orElse(null);
  }

  public Building saveBuilding(Building building) {
    return buildingRepository.save(building);
  }

  public void deleteBuilding(Long id) {
    buildingRepository.deleteById(id);
  }
}
