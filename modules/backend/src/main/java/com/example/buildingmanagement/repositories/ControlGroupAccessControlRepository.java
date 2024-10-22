package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.ControlGroupAccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlGroupAccessControlRepository extends JpaRepository<ControlGroupAccessControl,Long> {
}
