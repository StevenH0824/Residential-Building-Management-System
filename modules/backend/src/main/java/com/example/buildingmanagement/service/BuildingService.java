package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.*;
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

  public Long getLatestBuildingId() {
    return buildingRepository.findTopByOrderByBuildingIdDesc()
      .map(Building::getBuildingId)
      .orElse(0L); // Return 0 if no buildings exist
  }

  public BuildingResponseDTO updateBuilding(Long id, BuildingRequestDTO request) {
    Building building = buildingRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Building not found"));

    building.setName(request.getName());
    building.setAddress(request.getAddress());

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
        .map(floor -> {
          // Create a BuildingDTO for the floor
          BuildingDTO floorBuilding = new BuildingDTO();
          floorBuilding.setBuildingId(floor.getBuilding().getBuildingId());
          floorBuilding.setName(floor.getBuilding().getName());
          floorBuilding.setAddress(floor.getBuilding().getAddress());

          // Optionally, gather room details if rooms exist
          String roomNumber = null;
          String roomDescription = null;

          // If you have a method to get rooms from the floor
          if (floor.getRooms() != null && !floor.getRooms().isEmpty()) {
            // Assuming you want to set the first room details (or modify this logic as needed)
            roomNumber = floor.getRooms().get(0).getRoomNumber();
            roomDescription = floor.getRooms().get(0).getRoomDescription();
          }

          return new FloorResponseDTO(
            floor.getFloorId(),
            floor.getNumber(),
            floor.getDescription(),
            floorBuilding,
            roomNumber,      // Pass roomNumber to the DTO
            roomDescription   // Pass roomDescription to the DTO
          );
        })
        .collect(Collectors.toList());

      response.setFloors(floorResponseDto); // Set the floors as FloorResponseDTO list
    } else {
      response.setFloors(new ArrayList<>()); // Ensure you don't return null
    }
    return response;
  }

}
