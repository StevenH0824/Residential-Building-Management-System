package com.example.buildingmanagement.repositories;
import com.example.buildingmanagement.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

}




