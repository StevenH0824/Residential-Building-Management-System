package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.AccessRequest;
import com.example.buildingmanagement.service.AccessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access-requests")
public class AccessRequestController {
  private final AccessRequestService service;

  @Autowired
  public AccessRequestController(AccessRequestService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<AccessRequest> createRequest(@RequestBody AccessRequest accessRequest) {
    AccessRequest createdRequest = service.save(accessRequest);
    return ResponseEntity.ok(createdRequest);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccessRequest> getRequestById(@PathVariable Long id) {
    AccessRequest accessRequest = service.findById(id);
    return accessRequest != null ? ResponseEntity.ok(accessRequest) : ResponseEntity.notFound().build();
  }

  @GetMapping
  public ResponseEntity<List<AccessRequest>> getAllRequests() {
    List<AccessRequest> requests = service.findAll();
    return ResponseEntity.ok(requests);
  }

  @GetMapping("/approved/{approved}")
  public ResponseEntity<List<AccessRequest>> getRequestsByApproved(@PathVariable boolean approved) {
    List<AccessRequest> requests = service.findByApproved(approved);
    return ResponseEntity.ok(requests);
  }

  @GetMapping("/person/{personId}")
  public ResponseEntity<List<AccessRequest>> getRequestsByPersonId(@PathVariable Long personId) {
    List<AccessRequest> requests = service.findByPersonId(personId);
    return ResponseEntity.ok(requests);
  }

  @GetMapping("/access-control/{accessControlId}")
  public ResponseEntity<List<AccessRequest>> getRequestsByAccessControlId(@PathVariable Long accessControlId) {
    List<AccessRequest> requests = service.findByAccessControlId(accessControlId);
    return ResponseEntity.ok(requests);
  }

  @GetMapping("/scanner/{cardScannerId}")
  public ResponseEntity<List<AccessRequest>> getRequestsByCardScannerId(@PathVariable Long cardScannerId) {
    List<AccessRequest> requests = service.findByCardScanner_CardScannerId(cardScannerId);
    return ResponseEntity.ok(requests);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccessRequest> updateRequest(@PathVariable Long id, @RequestBody AccessRequest accessRequest) {
    accessRequest.setId(id); // Ensure the ID matches for the update
    AccessRequest updatedRequest = service.update(accessRequest);
    return updatedRequest != null ? ResponseEntity.ok(updatedRequest) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}


//package com.example.buildingmanagement.controller;
//
//import com.example.buildingmanagement.entities.AccessControl;
//import com.example.buildingmanagement.service.AccessControlService;
//import com.example.buildingmanagement.service.AccessRequestService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//public class AccessRequestController {
//  private final AccessRequestService accessRequestService;
//
//  public AccessRequestController(AccessRequestService accessRequestService) {
//    this.accessRequestService = accessRequestService;
//  }
//
//}
//
////@GetMapping
////public ResponseEntity<List<AccessControl>> getAllAccessControls() {
////  List<AccessControl> accessControls = accessControlService.getAllAccessControls();
////  return ResponseEntity.ok(accessControls);
////}
