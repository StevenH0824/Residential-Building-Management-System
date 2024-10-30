package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
  private final RoomRepository roomRepository;
  private final FloorRepository floorRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public RoomService(RoomRepository roomRepository, FloorRepository floorRepository, ModelMapper modelMapper) {
    this.roomRepository = roomRepository;
    this.floorRepository = floorRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public RoomResponseDTO getRoomById(Long id) {
    Room roomEntity = roomRepository.findByRoomId(id);
    assert roomEntity != null;
    return convertToRoomResponseDTO(roomEntity);
  }

  @Transactional
  public List<RoomResponseDTO> getRoomByNumber(String number) {
    List<Room> roomEntities = roomRepository.findByNumber(number);
    assert roomEntities != null;
    return convertToRoomResponseDTOList(roomEntities);
  }

  @Transactional
  public List<RoomResponseDTO> getRoomByDescription(String description) {
    List<Room> roomEntities = roomRepository.findByDescription(description);
    assert roomEntities != null;
    return convertToRoomResponseDTOList(roomEntities);
  }

  @Transactional
  public List<RoomResponseDTO> getRoomByFloorId(Long id) {
    Floor floor = floorRepository.findByFloorId(id);
    List<Room> roomEntities = roomRepository.findByFloor(floor);
    assert roomEntities != null;
    return convertToRoomResponseDTOList(roomEntities);
  }

  public List<RoomResponseDTO> getRoomsWithBuildingInfo() {
    return roomRepository.findRoomsWithBuildingInfo();
  }

  private RoomResponseDTO convertToRoomResponseDTO(Room room) {
    return new RoomResponseDTO(
      room.getRoomId(),
      room.getNumber(),
      room.getDescription(),
      room.getFloor() != null ? room.getFloor().getFloorId() : null,
      room.getFloor() != null ? room.getFloor().getDescription() : null,
      room.getFloor() != null && room.getFloor().getBuilding() != null ? room.getFloor().getBuilding().getBuildingId() : null,
      room.getFloor() != null && room.getFloor().getBuilding() != null ? room.getFloor().getBuilding().getName() : null,
      room.getFloor() != null && room.getFloor().getBuilding() != null ? room.getFloor().getBuilding().getAddress() : null
    );
  }

  private List<RoomResponseDTO> convertToRoomResponseDTOList(List<Room> rooms) {
    List<RoomResponseDTO> responseDTOs = new ArrayList<>();
    for (Room entity : rooms) {
      responseDTOs.add(convertToRoomResponseDTO(entity));
    }
    return responseDTOs;
  }
}
