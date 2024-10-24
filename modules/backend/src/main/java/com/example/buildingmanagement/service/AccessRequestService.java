package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.AccessRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessRequestService {
  @Autowired
  private final AccessRequestRepository repository;

  public AccessRequestService(AccessRequestRepository repository) {
    this.repository = repository;
  }

  public AccessRequest save(AccessRequest accessRequest) {
    return repository.save(accessRequest);
  }

  public AccessRequest findById(Long id) {
    return repository.findById(id).orElse(null); // Return null if not found
  }

  public List<AccessRequest> findAll() {
    return repository.findAll();
  }

  public List<AccessRequest> findByApproved(boolean approved) {
    return repository.findByApproved(approved);
  }

  public List<AccessRequest> findByPersonId(Long personId) {
    return repository.findByPerson_PersonId(personId);
  }

  public List<AccessRequest> findByAccessControlId(Long accessControlId) {
    return repository.findByAccessControl_AccessControlId(accessControlId);
  }

  public AccessRequest update(AccessRequest accessRequest) {
    // Check if the entity exists before saving
    if (repository.existsById(accessRequest.getId())) {
      return repository.save(accessRequest);
    } else {
      return null; // Or you can throw a custom exception
    }
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
