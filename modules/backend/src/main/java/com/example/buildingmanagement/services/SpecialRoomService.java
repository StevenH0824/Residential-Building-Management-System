package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.SpecialRoom;
import com.example.buildingmanagement.repositories.SpecialRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialRoomService {
  @Autowired
  private SpecialRoomRepository specialRoomRepository;

  public List<SpecialRoom> getAllSpecialRooms() {
    return specialRoomRepository.findAll();
  }

  public SpecialRoom getSpecialRoomById(Long id) {
    return specialRoomRepository.findById(id).orElse(null);
  }

  public SpecialRoom saveSpecialRoom(SpecialRoom specialRoom) {
    return specialRoomRepository.save(specialRoom);
  }

  public void deleteSpecialRoom(Long id) {
    specialRoomRepository.deleteById(id);
  }

}
