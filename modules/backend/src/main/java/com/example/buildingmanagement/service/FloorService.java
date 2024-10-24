package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {
  @Autowired
  private FloorRepository floorRepository;

  public List<Floor> getAllFloors() {
    return floorRepository.findAll();
  }

  public Floor getFloorById(Long id) {
    return floorRepository.findById(id).orElse(null);
  }

  public Floor saveFloor(Floor floor) {
    return floorRepository.save(floor);
  }

  public void deleteFloor(Long id) {
    floorRepository.deleteById(id);
  }
}
