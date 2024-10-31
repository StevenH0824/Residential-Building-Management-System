package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.ControlGroupPersonRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupPersonResponseDTO;
import com.example.buildingmanagement.entities.ControlGroupPersonId;
import com.example.buildingmanagement.service.ControlGroupPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-group-person")
public class ControlGroupPersonController {

  private final ControlGroupPersonService service;

  @Autowired
  public ControlGroupPersonController(ControlGroupPersonService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<ControlGroupPersonResponseDTO>> getAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{controlGroupId}/{personId}")
  public ResponseEntity<ControlGroupPersonResponseDTO> getById(@PathVariable Long controlGroupId, @PathVariable Long personId) {
    ControlGroupPersonId id = new ControlGroupPersonId(controlGroupId, personId);
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<ControlGroupPersonResponseDTO> create(@RequestBody ControlGroupPersonRequestDTO requestDTO) {
    return ResponseEntity.ok(service.save(requestDTO));
  }

  @DeleteMapping("/{controlGroupId}/{personId}")
  public ResponseEntity<Void> delete(@PathVariable Long controlGroupId, @PathVariable Long personId) {
    ControlGroupPersonId id = new ControlGroupPersonId(controlGroupId, personId);
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
