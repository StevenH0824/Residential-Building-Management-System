package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.ControlGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlGroupRepository extends JpaRepository<ControlGroup,Long> {
}
