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
  public RoomResponseDTO getRoomById(Long Id) {

    Room roomEntity = roomRepository.findByRoomId(Id);
    assert roomEntity != null;
    return new RoomResponseDTO(roomEntity.getRoomId(), roomEntity.getDescription(), roomEntity.getNumber(), roomEntity.getFloor().getFloorId(), roomEntity.getFloor().getDescription()
    );
  }

  @Transactional
  public List<RoomResponseDTO> getRoomByNumber(String Number) {
    List<Room> roomEntity = roomRepository.findByNumber(Number);
    assert roomEntity != null;
    List<RoomResponseDTO> responseDTOs = new ArrayList<>();
    for (Room entity : roomEntity) {
      RoomResponseDTO dto = new RoomResponseDTO(
        entity.getRoomId(),
        entity.getNumber(),
        entity.getDescription(),
        entity.getFloor().getFloorId(),
        entity.getFloor().getDescription()

      );
      responseDTOs.add(dto);
    }

    return responseDTOs;
  }


  @Transactional
  public List<RoomResponseDTO> getRoomByDescription(String Description) {
    List<Room> roomEntity = roomRepository.findByDescription(Description);
    assert roomEntity != null;
    List<RoomResponseDTO> responseDTOs = new ArrayList<>();
    for (Room entity : roomEntity) {
      RoomResponseDTO dto = new RoomResponseDTO(
        entity.getRoomId(),
        entity.getNumber(),
        entity.getDescription(),
        entity.getFloor().getFloorId(),
        entity.getFloor().getDescription()

      );
      responseDTOs.add(dto);
    }
    return responseDTOs;
  }


  @Transactional
  public List<RoomResponseDTO> getRoomByFloorId(Long Id) {
    Floor floor = floorRepository.findByFloorId(Id);
    List<Room> roomEntity = roomRepository.findByFloor(floor);
    assert roomEntity != null;
    List<RoomResponseDTO> responseDTOs = new ArrayList<>();
    for (Room entity : roomEntity) {
      RoomResponseDTO dto = new RoomResponseDTO(
        entity.getRoomId(),
        entity.getNumber(),
        entity.getDescription(),
        entity.getFloor().getFloorId(),
        entity.getFloor().getDescription()

      );
      responseDTOs.add(dto);
    }
    return responseDTOs;
  }
}
