package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/rooms")
@RestController
public class ApartmentController {
  @Autowired
  private RoomService roomService;

}
