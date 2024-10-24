package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.FloorDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorService {

  @Autowired
  private FloorRepository floorRepository;

  @Autowired
  private BuildingRepository buildingRepository;

  @Autowired
  private RoomRepository roomRepository;

  // Get all floors
  public List<FloorDTO> getAllFloors() {
    return floorRepository.findAll().stream()
      .map(this::convertToDTO)
      .collect(Collectors.toList());
  }

  // Get floor by ID
  public FloorDTO getFloorById(Long id) {
    return floorRepository.findById(id)
      .map(this::convertToDTO)
      .orElse(null);
  }

  // Create or update a floor
  public FloorDTO saveFloor(FloorDTO floorDTO) {
    if (floorDTO.getNumber() == null || floorDTO.getNumber().trim().isEmpty()) {
      throw new IllegalArgumentException("Floor number cannot be null or empty");
    }

    Building building = buildingRepository.findById(floorDTO.getBuildingId())
      .orElseThrow(() -> new IllegalArgumentException("Building not found"));

    Floor floor = new Floor();
    if (floorDTO.getFloorId() != null) {
      floor = floorRepository.findById(floorDTO.getFloorId()).orElse(new Floor());
    }
    floor.setNumber(floorDTO.getNumber());
    floor.setDescription(floorDTO.getDescription());
    floor.setBuilding(building);

    List<Room> rooms = roomRepository.findAllById(floorDTO.getRoomIds());
    floor.setRooms(rooms);

    Floor savedFloor = floorRepository.save(floor);
    return convertToDTO(savedFloor);
  }


  // Delete a floor
  public void deleteFloor(Long id) {
    floorRepository.deleteById(id);
  }

  // Convert Floor entity to FloorDTO
  private FloorDTO convertToDTO(Floor floor) {
    List<Long> roomIds = floor.getRooms().stream()
      .map(Room::getRoomId)
      .collect(Collectors.toList());
    return new FloorDTO(floor.getFloorId(), floor.getNumber(), floor.getDescription(), floor.getBuilding().getBuildingId(), roomIds);
  }
}
