package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.AccessControlRequestDTO;
import com.example.buildingmanagement.dtos.AccessControlResponseDTO;
import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.services.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("/{id}")
  public ResponseEntity<AccessControlResponseDTO> getAccessControlById(@PathVariable Long id) {
    AccessControl accessControl = accessControlService.getAccessControlById(id).orElse(null);
    if (accessControl == null) {
      return ResponseEntity.notFound().build();
    }
    AccessControlResponseDTO responseDTO = new AccessControlResponseDTO(
      accessControl.getAccessControlId(),
      accessControl.getDescription(),
      accessControl.getCardScanner().getCardScannerId(),
      accessControl.getCardScanner().getModel(),
      accessControl.getRoom().getRoomId(),
      accessControl.getRoom().getNumber()
    );
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping
  public AccessControlResponseDTO createAccessControl(@RequestBody AccessControlRequestDTO accessControlRequestDTO) {
    AccessControl accessControl = accessControlService.createAccessControl(accessControlRequestDTO);
    return new AccessControlResponseDTO(
      accessControl.getAccessControlId(),
      accessControl.getDescription(),
      accessControl.getCardScanner().getCardScannerId(),
      accessControl.getCardScanner().getModel(),
      accessControl.getRoom().getRoomId(),
      accessControl.getRoom().getNumber()
    );  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAccessControl(@PathVariable Long id) {
    accessControlService.deleteAccessControl(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/by-control-group/{controlGroupId}")
  public List<AccessControlResponseDTO> getAccessControlsByControlGroupId(@PathVariable Long controlGroupId) {
    List<AccessControl> accessControls = accessControlService.findByControlGroupId(controlGroupId);
    return accessControls.stream()
      .map(accessControl -> new AccessControlResponseDTO(
        accessControl.getAccessControlId(),
        accessControl.getDescription(),
        accessControl.getCardScanner().getCardScannerId(),
        accessControl.getCardScanner().getModel(),
        accessControl.getRoom().getRoomId(),
        accessControl.getRoom().getNumber()
      )).toList();  }

  @GetMapping("/by-room/{roomId}")
  public List<AccessControlResponseDTO> getAccessControlsByRoomId(@PathVariable Long roomId) {
    List<AccessControl> accessControls = accessControlService.findByRoomId(roomId);
    return accessControls.stream()
      .map(accessControl -> new AccessControlResponseDTO(
        accessControl.getAccessControlId(),
        accessControl.getDescription(),
        accessControl.getCardScanner().getCardScannerId(),
        accessControl.getCardScanner().getModel(),
        accessControl.getRoom().getRoomId(),
        accessControl.getRoom().getNumber()
      )).toList();
  }
}
