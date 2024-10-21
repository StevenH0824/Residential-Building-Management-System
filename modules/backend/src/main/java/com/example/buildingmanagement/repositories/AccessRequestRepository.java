package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.AccessRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessRequestRepository extends JpaRepository<AccessRequest,Long> {

}
