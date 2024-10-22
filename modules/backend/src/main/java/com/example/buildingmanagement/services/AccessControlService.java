package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.repositories.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessControlService {
  @Autowired
  private AccessControlRepository accessControlRepository;

  public List<AccessControl> findAll() {
    return accessControlRepository.findAll();
  }

  public Optional<AccessControl> findById(Long id) {
    return accessControlRepository.findById(id);
  }

  public AccessControl save(AccessControl accessControl) {
    return accessControlRepository.save(accessControl);
  }

  public void deleteById(Long id) {
    accessControlRepository.deleteById(id);
  }
}
