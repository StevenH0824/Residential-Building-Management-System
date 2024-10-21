//package com.example.buildingmanagement.services;
//
//import com.example.buildingmanagement.entities.Badge;
//import com.example.buildingmanagement.repositories.BadgeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BadgeService {
//
//  private final BadgeRepository repository;
//
//  @Autowired
//  public BadgeService(BadgeRepository repository) {
//    this.repository = repository;
//  }
//
//  public Badge save(Badge badge) {
//    return repository.save(badge);
//  }
//
////  public List<Badge> findByPersonId(Long personId) {
////    return repository.findByPersonId(personId);
////  }
//
//  public void delete(Long id) {
//    repository.deleteById(id);
//  }
//}
