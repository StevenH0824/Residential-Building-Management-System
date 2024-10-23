package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.service.AccessControlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access-controls")
public class AccessControlController {
  private final AccessControlService accessControlService;

  public AccessControlController(AccessControlService accessControlService) {
    this.accessControlService = accessControlService;
  }

  @GetMapping
  public ResponseEntity<List<AccessControl>> getAllAccessControls() {
    List<AccessControl> accessControls = accessControlService.getAllAccessControls();
    return ResponseEntity.ok(accessControls);
  }

}
