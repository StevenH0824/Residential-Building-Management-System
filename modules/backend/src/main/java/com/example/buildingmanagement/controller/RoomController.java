package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/rooms")
@RestController
public class RoomController {
  @Autowired
  private RoomService roomService;


  @GetMapping("/{roomId}")
  public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long roomId) {
    try {
      RoomResponseDTO room = roomService.getRoomById(roomId);
      return ResponseEntity.ok(room);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/rom/{roomNumber}")
  public ResponseEntity<List<RoomResponseDTO>> getRoomByNumber(@PathVariable String roomNumber) {
    try {
      List<RoomResponseDTO> room = roomService.getRoomByNumber(roomNumber);
      return ResponseEntity.ok(room);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/desc/{roomDescription}")
  public ResponseEntity<List<RoomResponseDTO>> getRoomByDescription(@PathVariable String roomDescription) {
    try {
      List<RoomResponseDTO> room = roomService.getRoomByDescription(roomDescription);
      return ResponseEntity.ok(room);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/flo/{floorId}")
  public ResponseEntity<List<RoomResponseDTO>> getRoomByFloorId(@PathVariable Long floorId) {
    try {
      List<RoomResponseDTO> room = roomService.getRoomByFloorId(floorId);
      return ResponseEntity.ok(room);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

  @GetMapping("/with-building-info")
  public ResponseEntity<List<RoomResponseDTO>> getRoomsWithBuildingInfo() {
    try {
      List<RoomResponseDTO> rooms = roomService.getRoomsWithBuildingInfo();
      return ResponseEntity.ok(rooms);
    } catch (ResponseStatusException ex) {
      return ResponseEntity.status(ex.getStatusCode()).body(null);
    }
  }

}

