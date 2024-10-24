package com.example.buildingmanagement.repository;
import com.example.buildingmanagement.entities.Floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
}

