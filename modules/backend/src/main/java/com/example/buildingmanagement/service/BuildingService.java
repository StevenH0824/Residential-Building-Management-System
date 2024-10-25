package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.dtos.BuildingResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {
  private final BuildingRepository buildingRepository;
  private final FloorRepository floorRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository, FloorRepository floorRepository){
    this.buildingRepository= buildingRepository;
    this.floorRepository = floorRepository;
  }

  public BuildingResponseDTO createBuilding(BuildingRequestDTO request) {
    Building building = new Building();
    building.setName(request.getName());
    building.setAddress(request.getAddress());
    building = buildingRepository.save(building);
    return mapToResponse(building);
  }

  public List<BuildingResponseDTO> getAllBuildings() {
    return buildingRepository.findAll().stream()
      .map(this::mapToResponse)
      .collect(Collectors.toList());
  }

  public BuildingResponseDTO getBuildingById(Long id) {
    Building building = buildingRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Building not found"));
    return mapToResponse(building);
  }

  public BuildingResponseDTO updateBuilding(Long id, BuildingRequestDTO request) {
    Building building = buildingRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Building not found"));

    building.setName(request.getName());
    building.setAddress(request.getAddress());
    // Update floors if needed
    building = buildingRepository.save(building);
    return mapToResponse(building);
  }

  public void deleteBuilding(Long id) {
    buildingRepository.deleteById(id);
  }

  private BuildingResponseDTO mapToResponse(Building building) {
    BuildingResponseDTO response = new BuildingResponseDTO();
    response.setBuildingId(building.getBuildingId());
    response.setName(building.getName());
    response.setAddress(building.getAddress());
    return response;
  }











}
