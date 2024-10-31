package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.entities.ControlGroupAccessControl;
import com.example.buildingmanagement.entities.ControlGroupAccessControlId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ControlGroupAccessControlRepository extends JpaRepository<ControlGroupAccessControl,ControlGroupAccessControlId> {
    List<ControlGroupAccessControl> findById(ControlGroup controlGroup);
}
