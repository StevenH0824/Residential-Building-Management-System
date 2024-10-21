package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.repositories.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessControlService {
  private final AccessControlRepository repository;

  @Autowired
  public AccessControlService(AccessControlRepository repository) {
    this.repository = repository;
  }
}

/*
public void createAccessControl(AccessControlDto dto) {
    AccessControl accessControl = new AccessControl();
    accessControl.setDescription(dto.getDescription());

    // Example: If the areaType is Apartment
    if (dto.getAreaType() == AreaType.A) {
        accessControl.setAreaId(dto.getApartmentId());
    } else if (dto.getAreaType() == AreaType.SR) {
        accessControl.setAreaId(dto.getSpecialRoomId());
    } else if (dto.getAreaType() == AreaType.F) {
        accessControl.setAreaId(dto.getFloorId());
    }

    // Save accessControl to the database
    accessControlRepository.save(accessControl);
}
 */
