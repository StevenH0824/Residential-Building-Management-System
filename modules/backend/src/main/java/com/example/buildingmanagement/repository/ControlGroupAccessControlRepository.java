package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.entities.ControlGroupAccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlGroupAccessControlRepository extends JpaRepository<ControlGroupAccessControl,Long> {
    List<ControlGroupAccessControl> findByControlGroup(ControlGroup controlGroup);
}
