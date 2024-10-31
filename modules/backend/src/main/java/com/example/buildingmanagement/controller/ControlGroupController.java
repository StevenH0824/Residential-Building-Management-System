package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.ControlGroupAccessControlDTO;
import com.example.buildingmanagement.dtos.ControlGroupRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupResponseDTO;
import com.example.buildingmanagement.entities.ControlGroup;
//import com.example.buildingmanagement.service.ControlGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/control-groups")
public class ControlGroupController {
//
//  @Autowired
//  private ControlGroupService controlGroupService;
//
//  @GetMapping
//  public List<ControlGroupResponseDTO> getAllControlGroups() {
//    return controlGroupService.findAll()
//      .stream()
//      .map(this::toResponseDTO)
//      .collect(Collectors.toList());
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity<ControlGroupResponseDTO> getControlGroupById(@PathVariable Long id) {
//    return controlGroupService.findById(id)
//      .map(this::toResponseDTO)
//      .map(ResponseEntity::ok)
//      .orElse(ResponseEntity.notFound().build());
//  }
//
//  @PostMapping
//  public ResponseEntity<ControlGroupResponseDTO> createControlGroup(@RequestBody ControlGroupRequestDTO controlGroupDTO) {
//    ControlGroup controlGroup = controlGroupService.fromRequestDTO(controlGroupDTO);
//    ControlGroupResponseDTO savedControlGroup = controlGroupService.save(controlGroup);
//
//    // Build the URI of the new resource
//    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//      .path("/{id}")
//      .buildAndExpand(savedControlGroup.getControlGroupId())
//      .toUri();
//
//    return ResponseEntity.created(uri)
//      .body(toResponseDTO(savedControlGroup));
//  }
//
//  @PutMapping("/{id}")
//  public ResponseEntity<ControlGroupResponseDTO> updateControlGroup(@PathVariable Long id, @RequestBody ControlGroupRequestDTO controlGroupDTO) {
//    if (!controlGroupService.findById(id).isPresent()) {
//      return ResponseEntity.notFound().build();
//    }
//    ControlGroup controlGroup = controlGroupService.fromRequestDTO(controlGroupDTO);
//    controlGroup.setControlGroupId(id); // Ensure the ID is set for the update
//    return ResponseEntity.ok(toResponseDTO(controlGroupService.save(controlGroup)));
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deleteControlGroup(@PathVariable Long id) {
//    if (!controlGroupService.findById(id).isPresent()) {
//      return ResponseEntity.notFound().build();
//    }
//    controlGroupService.deleteById(id);
//    return ResponseEntity.noContent().build();
//  }
//
//  public ControlGroup fromRequestDTO(ControlGroupRequestDTO dto) {
//    return new ControlGroup(
//      null, // ControlGroupId should be null for creation
//      dto.getName(),
//      dto.getDescription(),
//      controlGroupService.convertDTOsToAccessControls(dto.getAccessControls()) // Convert access controls if necessary
//    );
//  }
//
//  public ControlGroupResponseDTO toResponseDTO(ControlGroupResponseDTO controlGroup) {
//    return new ControlGroupResponseDTO(
//      controlGroup.getControlGroupId(),
//      controlGroup.getName(),
//      controlGroup.getDescription(),
//      convertAccessControlsToDTOs(controlGroup.getAccessControls()) // Convert access controls
//    );
//  }
//
//  private List<ControlGroupAccessControlDTO> convertAccessControlsToDTOs(List<ControlGroupAccessControlDTO> accessControls) {
//    return accessControls.stream()
//      .map(accessControl -> new ControlGroupAccessControlDTO(
//        accessControl.getControlGroup().getControlGroupId(), // or however you want to get the controlGroupId
//        accessControl.getAccessControl().getAccessControlId() // Assuming you have accessControlId in AccessControl entity
//      ))
//      .collect(Collectors.toList());
//  }
}
