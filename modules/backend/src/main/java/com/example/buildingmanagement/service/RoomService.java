package com.example.buildingmanagement.service;

import com.example.buildingmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
  @Autowired
  private RoomRepository apartmentRepository;
}
