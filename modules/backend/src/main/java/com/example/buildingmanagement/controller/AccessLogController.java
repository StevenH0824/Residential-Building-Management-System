package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.AccessLogRequestDTO;
import com.example.buildingmanagement.dtos.AccessLogResponseDTO;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {

  @Autowired
  private AccessLogService accessLogService;

  // Endpoint to log access
  @PostMapping("/log")
  public ResponseEntity<AccessLogResponseDTO> logAccess(@RequestBody AccessLogRequestDTO accessLogRequestDTO) {
    AccessLogResponseDTO accessLogResponseDTO = accessLogService.createAccessLog(accessLogRequestDTO);
    return ResponseEntity.ok(accessLogResponseDTO);
  }

  // Endpoint to get all access logs
  @GetMapping
  public ResponseEntity<List<AccessLogResponseDTO>> getAllAccessLogs() {
    List<AccessLogResponseDTO> accessLogs = accessLogService.getAllAccessLogs().stream()
      .map(accessLog -> {
        AccessLogResponseDTO dto = new AccessLogResponseDTO();
        dto.setAccessLogId(accessLog.getAccessLogId());
        dto.setCardScannerId(accessLog.getCardScanner().getId());

        // Ensure that the room is fetched correctly
        if (accessLog.getCardScanner().getRoom() != null) {
          dto.setFloorDescription(accessLog.getCardScanner().getRoom().getFloor().getDescription());
          dto.setRoomNumber(accessLog.getCardScanner().getRoom().getRoomNumber());
          dto.setBuildingName(accessLog.getCardScanner().getRoom().getBuilding().getName());
        }

        dto.setAccessTime(accessLog.getAccessTime());

        // Assuming accessLog has a method to get the person who accessed
        Person person = accessLog.getPerson();
        if (person != null) {
          dto.setFullName(person.getFirstName() + " " + person.getLastName());
        }

        return dto;
      })
      .collect(Collectors.toList());

    return ResponseEntity.ok(accessLogs);
  }

  // Endpoint to delete an access log by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAccessLog(@PathVariable Long id) {
    accessLogService.deleteAccessLog(id);
    return ResponseEntity.noContent().build();
  }
}
