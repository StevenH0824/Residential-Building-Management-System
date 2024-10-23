package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest,Long> {
}
