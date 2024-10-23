package com.example.buildingmanagement.Services;

import com.example.buildingmanagement.DTOs.MaintenanceRequestDTO;


import java.time.LocalDateTime;


public interface MaintenanceServices {
   MaintenanceRequestDTO getByPersonId(Long id);
   MaintenanceRequestDTO getByApartmentId(Long apartmentId);
   MaintenanceRequestDTO getByRequestDate(LocalDateTime requestDate);

}
