package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository <MaintenanceRequest, Long > {

    MaintenanceRequest findByMaintenanceRequestId(Long id);
    MaintenanceRequest findByPerson(Person id); //optional removed "fixed" service
    List <MaintenanceRequest> findByStatus(StatusType status);
    MaintenanceRequest findByRoom(Room id);
    List<MaintenanceRequest> findByCreatedDate(LocalDateTime createdDate);
    List<MaintenanceRequest> findByEndDate(LocalDateTime endDate);
//    List<MaintenanceRequest> findAllRequests();



}
