package com.example.buildingmanagement.repository;
import com.example.buildingmanagement.dtos.FloorResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
  Floor findByFloorId(Long id);
  @Query("SELECT f FROM Floor f WHERE f.building.id = :buildingId")
  List<Floor> findByBuildingId(@Param("buildingId") Long buildingId);
  }

