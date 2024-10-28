package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.dtos.RoomDTO;
import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
  private final RoomRepository apartmentRepository;
  private final FloorRepository floorRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public RoomService(RoomRepository apartmentRepository, FloorRepository floorRepository, ModelMapper modelMapper) {
    this.apartmentRepository = apartmentRepository;
    this.floorRepository = floorRepository;
    this.modelMapper = modelMapper;
  }

//  public List<RoomResponseDTO> getAllRooms() {
//    return apartmentRepository.findAll();
//  }


  @Transactional
  public RoomResponseDTO getRoomById(Long Id) {

    Room roomEntity = apartmentRepository.findByRoomId(Id);
    assert roomEntity != null;
    return new RoomResponseDTO(roomEntity.getRoomId(), roomEntity.getDescription(), roomEntity.getNumber(), roomEntity.getFloor().getFloorId(), roomEntity.getFloor().getDescription()
    );
  }

  @Transactional
  public List<RoomResponseDTO> getRoomByNumber(String Number) {
    List<Room> roomEntity = apartmentRepository.findByNumber(Number);
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
    List<Room> roomEntity = apartmentRepository.findByDescription(Description);
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
    List<Room> roomEntity = apartmentRepository.findByFloor(floor);
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


//    return roomEntity.stream().map(room -> modelMapper.map(room, RoomResponseDTO.class)).collect(Collectors.toList());
  }
//    Room roomEntity = apartmentRepository.findByFloor(floor);
//    assert roomEntity != null;
//    return new RoomResponseDTO(roomEntity.getRoomId(),roomEntity.getNumber(),roomEntity.getDescription(), roomEntity.getFloor().getFloorId(), roomEntity.getFloor().getDescription());
//    return modelMapper.map(roomEntity, RoomResponseDTO.class);

}
