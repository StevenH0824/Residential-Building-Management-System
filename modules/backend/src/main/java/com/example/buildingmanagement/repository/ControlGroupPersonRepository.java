package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.ControlGroupPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlGroupPersonRepository extends JpaRepository<ControlGroupPerson,Long> {
}
