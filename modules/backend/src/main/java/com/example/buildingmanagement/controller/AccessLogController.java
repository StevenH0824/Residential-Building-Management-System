package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.AccessLogRequestDTO;
import com.example.buildingmanagement.dtos.AccessLogResponseDTO;
import com.example.buildingmanagement.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accesslog")
public class AccessLogController {

  @Autowired
  private AccessLogService accessLogService;

  // Endpoint to log access
  @PostMapping("/log")
  public ResponseEntity<AccessLogResponseDTO> logAccess(@RequestBody AccessLogRequestDTO accessLogRequestDTO) {
    AccessLogResponseDTO accessLogResponseDTO = accessLogService.logAccess(accessLogRequestDTO);
    return ResponseEntity.ok(accessLogResponseDTO);
  }

  // Endpoint to get all access logs
  @GetMapping
  public ResponseEntity<List<AccessLogResponseDTO>> getAllAccessLogs() {
    List<AccessLogResponseDTO> accessLogs = accessLogService.getAllAccessLogs();
    return ResponseEntity.ok(accessLogs);
  }

  // Endpoint to delete an access log by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAccessLog(@PathVariable Long id) {
    accessLogService.deleteAccessLog(id);
    return ResponseEntity.noContent().build();
  }
}
