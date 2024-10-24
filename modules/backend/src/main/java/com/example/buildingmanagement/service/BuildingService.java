package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingDTO;
import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {
  @Autowired
  private BuildingRepository buildingRepository;

  public List<BuildingDTO> getAllBuildings() {
    return buildingRepository.findAll().stream()
      .map(this::convertToResponseDTO)
      .collect(Collectors.toList());
  }

  public BuildingDTO getBuildingById(Long id) {
    return buildingRepository.findById(id)
      .map(this::convertToResponseDTO)
      .orElse(null); // Handle not found as needed
  }

  public BuildingDTO saveBuilding(BuildingRequestDTO buildingDTO) {
    Building building = convertToEntity(buildingDTO);
    Building savedBuilding = buildingRepository.save(building);
    return convertToResponseDTO(savedBuilding);
  }

  public void deleteBuilding(Long id) {
    buildingRepository.deleteById(id);
  }

  private Building convertToEntity(BuildingRequestDTO dto) {
    Building building = new Building();
    building.setBuildingId(dto.getBuildingId());
    building.setName(dto.getName());
    building.setAddress(dto.getAddress());

    // Convert floor IDs to Floor entities
    List<Floor> floors = dto.getFloorIds().stream()
      .map(id -> {
        Floor floor = new Floor(); // Assuming a constructor or method to fetch floor by ID
        floor.setFloorId(id); // Set the ID
        return floor;
      })
      .collect(Collectors.toList());
    building.setFloors(floors);

    return building;
  }

  private BuildingDTO convertToResponseDTO(Building building) {
    List<String> floorIds = building.getFloors().stream()
      .map(Floor::getNumber)
      .collect(Collectors.toList());
    return new BuildingDTO(building.getBuildingId(), building.getName(), building.getAddress(), floorIds);
  }
}
