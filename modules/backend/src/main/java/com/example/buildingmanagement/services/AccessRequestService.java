package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.AccessRequest;
import com.example.buildingmanagement.repositories.AccessRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessRequestService {
  private final AccessRequestRepository repository;

  @Autowired
  public AccessRequestService(AccessRequestRepository repository) {
    this.repository = repository;
  }

  public AccessRequest save(AccessRequest accessRequest) {
    return repository.save(accessRequest);
  }

//  public Optional<AccessRequest> findByAccessControlId(Long accessControl) {
//    return repository.findById(accessControl);
////    return repository.findByAccessControlPerson_Id(accessControlPersonId);
//  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}
