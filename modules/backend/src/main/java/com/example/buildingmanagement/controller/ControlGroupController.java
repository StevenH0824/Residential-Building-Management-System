package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.ControlGroupRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupResponseDTO;
import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.service.ControlGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-groups")
public class ControlGroupController {

  private final ControlGroupService controlGroupService;

  @Autowired
  public ControlGroupController(ControlGroupService controlGroupService) {
    this.controlGroupService = controlGroupService;
  }

  @GetMapping
  public List<ControlGroupResponseDTO> getAllControlGroups() {
    return controlGroupService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ControlGroupResponseDTO> getControlGroupById(@PathVariable Long id) {
    return controlGroupService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  // Not working
  @PostMapping
  public ControlGroupResponseDTO createControlGroup(@RequestBody ControlGroupRequestDTO controlGroupRequest) {
    return controlGroupService.save(controlGroupRequest);
  }

  // Not working
  @PutMapping("/{id}")
  public ResponseEntity<ControlGroupResponseDTO> updateControlGroup(@PathVariable Long id, @RequestBody ControlGroupRequestDTO controlGroupRequest) {
    ControlGroupResponseDTO updatedGroup = controlGroupService.save(controlGroupRequest);
    updatedGroup.setControlGroupId(id); // Ensure the ID is set for the response
    return ResponseEntity.ok(updatedGroup);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteControlGroup(@PathVariable Long id) {
    controlGroupService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
