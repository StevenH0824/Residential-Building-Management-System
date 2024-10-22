package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.repositories.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccessControlService {
  private final AccessControlRepository accessControlRepository;

  public AccessControlService(AccessControlRepository accessControlRepository) {
    this.accessControlRepository = accessControlRepository;
  }
  @Transactional
  public List<AccessControl> getAllAccessControls() {
    return accessControlRepository.findAll();
  }
}
