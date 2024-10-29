package com.example.buildingmanagement.service;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService {
  private static final Logger logger = LoggerFactory.getLogger(FloorService.class);

  private final FloorRepository floorRepository;
  private final BuildingRepository buildingRepository;
  private final RoomRepository roomRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  public FloorService(FloorRepository floorRepository, BuildingRepository buildingRepository, RoomRepository roomRepository){
    this.floorRepository = floorRepository;
    this.buildingRepository =buildingRepository;
    this.roomRepository=roomRepository;
  }

  // Get all floors
  public List<FloorRequestDTO> getAllFloors() {
    return floorRepository.findAll().stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  // Get floor by ID
  public FloorRequestDTO getFloorById(Long id) {
    return floorRepository.findById(id)
      .map(this::convertToDTO)
      .orElse(null);
  }

  // Create or update a floor
  public FloorRequestDTO saveFloor(FloorRequestDTO floorRequestDTO) {
    if (floorRequestDTO.getNumber() == null || floorRequestDTO.getNumber().trim().isEmpty()) {
      throw new IllegalArgumentException("Floor number cannot be null or empty");
    }

    Building building = buildingRepository.findById(floorRequestDTO.getBuildingId())
      .orElseThrow(() -> new IllegalArgumentException("Building not found"));

    Floor floor = new Floor();
    if (floorRequestDTO.getFloorId() != null) {
      floor = floorRepository.findById(floorRequestDTO.getFloorId()).orElse(new Floor());
    }
    floor.setNumber(floorRequestDTO.getNumber());
    floor.setDescription(floorRequestDTO.getDescription());
    floor.setBuilding(building);

    List<Room> rooms = roomRepository.findAllById(floorRequestDTO.getRoomIds());
    floor.setRooms(rooms);

    Floor savedFloor = floorRepository.save(floor);
    return convertToDTO(savedFloor);
  }


  // Delete a floor
  public void deleteFloor(Long id) {
    floorRepository.deleteById(id);
  }

  // Convert Floor entity to FloorDTO
  private FloorRequestDTO convertToDTO(Floor floor) {
    List<Long> roomIds = floor.getRooms().stream()
      .map(Room::getRoomId)
      .collect(Collectors.toList());
    return new FloorRequestDTO(floor.getFloorId(), floor.getNumber(), floor.getDescription(), floor.getBuilding().getBuildingId(), roomIds);
  }

  public List<FloorResponseDTO> getFloorsByBuildingId(Long buildingId) {
    try {
      Building building = buildingRepository.findById(buildingId)
        .orElseThrow(() -> new EntityNotFoundException("Building not found"));

      List<Floor> floors = floorRepository.findByBuildingId(buildingId);
      return floors.stream()
        .map(floor -> modelMapper.map(floor, FloorResponseDTO.class))
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Error fetching floors for building ID {}: {}", buildingId, e.getMessage());
      throw e;
    }
  }
  }

