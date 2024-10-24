package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.AccessRequest;
import com.example.buildingmanagement.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
  List<AccessRequest> findByApproved(boolean approved);
  List<AccessRequest> findByPerson_PersonId(Long personId);
  List<AccessRequest> findByAccessControl_AccessControlId(Long accessControlId);
}
