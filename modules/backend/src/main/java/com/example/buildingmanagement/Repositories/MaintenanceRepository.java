package com.example.buildingmanagement.Repositories;

import com.example.buildingmanagement.entities.Apartment;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface MaintenanceRepository extends JpaRepository <MaintenanceRequest, Long > {

  MaintenanceRequest findByMaintenanceRequestId(Long id);
  Optional<MaintenanceRequest> findByStatus(StatusType status);
  Optional<MaintenanceRequest> findByPersonId(Person person);
  Optional<MaintenanceRequest> findByApartment(Apartment apartment);
  Optional<MaintenanceRequest> findByRequestDate(LocalDateTime requestDate);
  Optional<MaintenanceRequest> findByResolvedDate(LocalDateTime resolvedDate);
}
