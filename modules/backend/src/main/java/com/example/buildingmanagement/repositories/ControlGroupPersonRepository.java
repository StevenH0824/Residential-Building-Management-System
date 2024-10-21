package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.ControlGroupPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlGroupPersonRepository extends JpaRepository<ControlGroupPerson,Long> {
}
