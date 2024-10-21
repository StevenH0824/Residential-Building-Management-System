package com.example.buildingmanagement.repositories;
import com.example.buildingmanagement.entities.Floor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor,Long> {
}

