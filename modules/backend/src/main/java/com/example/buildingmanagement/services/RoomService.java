package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
  @Autowired
  private RoomRepository apartmentRepository;
}
