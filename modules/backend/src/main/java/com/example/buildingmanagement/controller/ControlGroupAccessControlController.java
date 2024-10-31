package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.ControlGroupAccessControlRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupAccessControlResponseDTO;
import com.example.buildingmanagement.entities.ControlGroupAccessControlId;
import com.example.buildingmanagement.service.ControlGroupAccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-group-access-controls")
public class ControlGroupAccessControlController {

  private final ControlGroupAccessControlService service;

  @Autowired
  public ControlGroupAccessControlController(ControlGroupAccessControlService service) {
    this.service = service;
  }

  @GetMapping
  public List<ControlGroupAccessControlResponseDTO> getAllAccessControls() {
    return service.findAll();
  }

  @GetMapping("/{controlGroupId}/{accessControlId}")
  public ResponseEntity<ControlGroupAccessControlResponseDTO> getAccessControlById(
    @PathVariable Long controlGroupId, @PathVariable Long accessControlId) {
    ControlGroupAccessControlId id = new ControlGroupAccessControlId(controlGroupId, accessControlId);
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ControlGroupAccessControlResponseDTO createAccessControl(@RequestBody ControlGroupAccessControlRequestDTO requestDTO) {
    return service.save(requestDTO);
  }

  @DeleteMapping("/{controlGroupId}/{accessControlId}")
  public ResponseEntity<Void> deleteAccessControl(
    @PathVariable Long controlGroupId, @PathVariable Long accessControlId) {
    ControlGroupAccessControlId id = new ControlGroupAccessControlId(controlGroupId, accessControlId);
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
