package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRequestRepository extends JpaRepository<AccessRequest,Long> {

}
