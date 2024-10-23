//package com.example.buildingmanagement.services;
//
//import com.example.buildingmanagement.entities.AccessControlPerson;
//import com.example.buildingmanagement.entities.Person;
//import com.example.buildingmanagement.repositories.AccessControlPersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AccessControlPersonService {
//  private final AccessControlPersonRepository repository;
//
//  @Autowired
//  public AccessControlPersonService(AccessControlPersonRepository repository) {
//    this.repository = repository;
//  }
//
//  public AccessControlPerson save(AccessControlPerson accessControlPerson) {
//    return repository.save(accessControlPerson);
//  }
//
//  public List<AccessControlPerson> findByPersonId(Person personId) {
//    return repository.findByPersonId(personId);
//  }
//
//  public List<AccessControlPerson> findByAccessControlId(Long accessControlId) {
//    return repository.findByAccessControl_AccessControlId(accessControlId);
//  }
//
//  public void delete(Long id) {
//    repository.deleteById(id);
//  }
//
//}
