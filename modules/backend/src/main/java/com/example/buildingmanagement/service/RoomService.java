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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
  @Autowired
  private RoomRepository apartmentRepository;
  @Autowired
  private FloorRepository floorRepository;

//  public List<RoomResponseDTO> getAllRooms() {
//    return apartmentRepository.findAll();
//  }


  @Transactional
  public RoomResponseDTO getRoomById(Long Id) {

    Room roomEntity = apartmentRepository.findByRoomId(Id);
    assert roomEntity != null;
    return new RoomResponseDTO(roomEntity.getRoomId(), roomEntity.getDescription(), roomEntity.getFloor().getFloorId()
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
        entity.getFloor()

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
        entity.getFloor()

      );
      responseDTOs.add(dto);
    }

    return responseDTOs;
  }

//
//  @Transactional
//  public List<RoomResponseDTO> getRoomByFloorId(Floor Id) {
//    Floor floor = floorRepository.;
//    List<Room> roomEntity = apartmentRepository.findByFloor(Id);
//    assert roomEntity != null;
//    List<RoomResponseDTO> responseDTOs = new ArrayList<>();
//    for (Room entity : roomEntity) {
//      RoomResponseDTO dto = new RoomResponseDTO(
//        entity.getRoomId(),
//        entity.getNumber(),
//        entity.getDescription(),
//        entity.getFloor()
//
//      );
//      responseDTOs.add(dto);
//    }
//
//    return responseDTOs;
//  }





}
