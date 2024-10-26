package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.dtos.BuildingResponseDTO;
import com.example.buildingmanagement.dtos.FloorResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.repository.FloorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BuildingService {
  private final BuildingRepository buildingRepository;
  private final FloorRepository floorRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository, FloorRepository floorRepository) {
    this.buildingRepository = buildingRepository;
    this.floorRepository = floorRepository;
  }

  @Transactional
  public BuildingResponseDTO createBuilding(BuildingRequestDTO request) {
    Building building = new Building();
    building.setName(request.getName());
    building.setAddress(request.getAddress());

    // No need to create floors; just save the building
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

    // If you want to keep the option to update floors, include that logic here
    setFloorsForBuilding(building, request.getFloorIds());

    building = buildingRepository.save(building);
    return mapToResponse(building);
  }

  public void deleteBuilding(Long id) {
    buildingRepository.deleteById(id);
  }

  private void setFloorsForBuilding(Building building, List<Long> floorIds) {
    List<Floor> floors = floorRepository.findAllById(floorIds); // Fetch floors by IDs
    building.setFloors(floors); // Set floors to the building entity
  }

  private BuildingResponseDTO mapToResponse(Building building) {
    BuildingResponseDTO response = new BuildingResponseDTO();
    response.setBuildingId(building.getBuildingId());
    response.setName(building.getName());
    response.setAddress(building.getAddress());

    // Map each Floor to a FloorResponseDTO if floors exist
    if (building.getFloors() != null) {
      List<FloorResponseDTO> floorResponseDto = building.getFloors().stream()
        .map(floor -> new FloorResponseDTO(floor.getFloorId(), floor.getNumber(), floor.getDescription()))
        .collect(Collectors.toList());
      response.setFloors(floorResponseDto); // Set the floors as FloorResponseDTO list
    } else {
      response.setFloors(new ArrayList<>()); // Ensure you don't return null
    }
    return response;
  }
}
