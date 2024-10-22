package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.services.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access-controls")
public class AccessControlController {
  @Autowired
  private AccessControlService accessControlService;

  @GetMapping
  public List<AccessControl> getAllAccessControls() {
    return accessControlService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccessControl> getAccessControlById(@PathVariable Long id) {
    return accessControlService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public AccessControl createAccessControl(@RequestBody AccessControl accessControl) {
    return accessControlService.save(accessControl);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccessControl> updateAccessControl(
    @PathVariable Long id,
    @RequestBody AccessControl accessControl) {
    return accessControlService.findById(id)
      .map(existingAccessControl -> {
        accessControl.setAccessControlId(id);
        return ResponseEntity.ok(accessControlService.save(accessControl));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAccessControl(@PathVariable Long id) {
    accessControlService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
