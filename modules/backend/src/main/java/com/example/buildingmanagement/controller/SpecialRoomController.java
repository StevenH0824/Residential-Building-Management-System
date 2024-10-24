//package com.example.buildingmanagement.controller;
//
//import com.example.buildingmanagement.entities.SpecialRoom;
//import com.example.buildingmanagement.services.SpecialRoomService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RequestMapping("/api/specialRooms")
//@RestController
//public class SpecialRoomController {
//  @Autowired
//  private SpecialRoomService specialRoomService;
//
//  @GetMapping
//  public List<SpecialRoom> getAllSpecialRooms() {
//    return specialRoomService.getAllSpecialRooms();
//  }
//
//  @GetMapping("/{id}")
//  public ResponseEntity<SpecialRoom> getSpecialRoomById(@PathVariable Long id) {
//    SpecialRoom specialRoom = specialRoomService.getSpecialRoomById(id);
//    return specialRoom != null ? ResponseEntity.ok(specialRoom) : ResponseEntity.notFound().build();
//  }
//
//  @PostMapping
//  public SpecialRoom createSpecialRoom(@RequestBody SpecialRoom specialRoom) {
//    return specialRoomService.saveSpecialRoom(specialRoom);
//  }
//
//  @DeleteMapping("/{id}")
//  public void deleteSpecialRoom(@PathVariable Long id) {
//    specialRoomService.deleteSpecialRoom(id);
//  }
//}
