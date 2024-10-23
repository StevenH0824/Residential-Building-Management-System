package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.repository.AccessControlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
