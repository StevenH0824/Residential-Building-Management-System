package com.example.buildingmanagement.repository;
import com.example.buildingmanagement.entities.Building;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
  @EntityGraph(attributePaths = {"floors"})
  List<Building> findAll();

  @EntityGraph(attributePaths = {"floors"})
  Optional<Building> findById(Long id);
}




