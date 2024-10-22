package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/rooms")
@RestController
public class ApartmentController {
  @Autowired
  private RoomService roomService;

}
