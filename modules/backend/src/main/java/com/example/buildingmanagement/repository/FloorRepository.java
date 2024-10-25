package com.example.buildingmanagement.repository;
import com.example.buildingmanagement.entities.Floor;

import com.example.buildingmanagement.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
  Floor findByFloorId(Long id);

}

