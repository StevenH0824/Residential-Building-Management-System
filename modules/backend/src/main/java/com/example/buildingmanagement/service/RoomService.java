package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.RoomDto;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private FloorRepository floorRepository;

  public Room getRoom(RoomDto roomDto){
    Room room = new Room();
    room.setNumber(roomDto.getNumber());
    room.setDescription(room.getDescription());
    room.setFloor(floorRepository.getReferenceById(roomDto.getFloor().getFloorId()));
    return roomRepository.save(room);
  }
}
