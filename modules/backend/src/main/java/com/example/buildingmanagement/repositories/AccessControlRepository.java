package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
//  List<AccessControl> findByPerson_LastName(String lastName);
//  List<AccessControl> findByPerson_FirstName(String firstName);
//  List<AccessControl> findByPerson_PersonId(Long personId);
//  List<AccessControl> findByControlGroup_ControlGroupId(Long controlGroupId);
}

