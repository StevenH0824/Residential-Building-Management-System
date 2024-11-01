package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingDTO;
import com.example.buildingmanagement.dtos.FloorRequestDTO;
import com.example.buildingmanagement.dtos.FloorResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService {
  private static final Logger logger = LoggerFactory.getLogger(FloorService.class);

  private final FloorRepository floorRepository;
  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public FloorService(FloorRepository floorRepository, BuildingRepository buildingRepository,
                      RoomRepository roomRepository, ModelMapper modelMapper) {
    this.floorRepository = floorRepository;
    this.buildingRepository = buildingRepository;
    this.roomRepository = roomRepository;
    this.modelMapper = modelMapper;
  }

  // Get all floors
  public List<FloorResponseDTO> getAllFloors() {
    return floorRepository.findAll().stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  // Get floor by ID
  public ResponseEntity<FloorResponseDTO> getFloorById(Long id) {
    return floorRepository.findById(id)
      .map(this::convertToDTO)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  // Create or update a floor
  public Floor saveFloor(FloorRequestDTO floorRequestDTO) {
    validateFloorRequest(floorRequestDTO);

    // Check if the building exists
    Building building = buildingRepository.findById(floorRequestDTO.getBuildingId())
      .orElseThrow(() -> new EntityNotFoundException("Building not found with ID: " + floorRequestDTO.getBuildingId()));

    Floor floor = new Floor();
    floor.setNumber(floorRequestDTO.getNumber());
    floor.setDescription(floorRequestDTO.getDescription());
    floor.setBuilding(building);

    // Handle room IDs
    if (floorRequestDTO.getRoomIds() != null) {
      List<Room> rooms = roomRepository.findAllById(floorRequestDTO.getRoomIds());
      if (rooms.size() != floorRequestDTO.getRoomIds().size()) {
        throw new IllegalArgumentException("One or more room IDs are invalid.");
      }
      floor.setRooms(rooms);
    }

    return floorRepository.save(floor);
  }

  // Delete a floor
  public void deleteFloor(Long id) {
    if (!floorRepository.existsById(id)) {
      throw new EntityNotFoundException("Floor not found");
    }
    floorRepository.deleteById(id);
  }

  // Convert Floor entity to FloorResponseDTO
  private FloorResponseDTO convertToDTO(Floor floor) {
    List<Long> roomIds = floor.getRooms().stream()
      .map(Room::getRoomId)
      .collect(Collectors.toList());
    return new FloorResponseDTO(
      floor.getFloorId(),
      floor.getNumber(),
      floor.getDescription(),
      new BuildingDTO(
        floor.getBuilding().getBuildingId(),
        floor.getBuilding().getName(),
        floor.getBuilding().getAddress()),
      null, // Optional: room number
      null  // Optional: room description
    );
  }

  // Validate FloorRequestDTO
  private void validateFloorRequest(FloorRequestDTO floorRequestDTO) {
    if (floorRequestDTO.getNumber() == null || floorRequestDTO.getNumber().trim().isEmpty()) {
      throw new IllegalArgumentException("Floor number cannot be null or empty");
    }
  }

  // Get floors by building ID
  public List<FloorResponseDTO> getFloorsByBuildingId(Long buildingId) {
    Building building = buildingRepository.findById(buildingId)
      .orElseThrow(() -> new EntityNotFoundException("Building not found"));

    List<Floor> floors = floorRepository.findByBuildingId(buildingId);
    return floors.stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }
}
