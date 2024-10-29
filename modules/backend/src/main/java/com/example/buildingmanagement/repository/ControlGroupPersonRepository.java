package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.ControlGroupPerson;
import com.example.buildingmanagement.entities.ControlGroupPersonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlGroupPersonRepository extends JpaRepository<ControlGroupPerson, ControlGroupPersonId> {
  List<ControlGroupPerson> findByPerson_PersonId(Long personId);
}
