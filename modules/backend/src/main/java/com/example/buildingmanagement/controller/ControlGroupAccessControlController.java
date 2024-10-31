//package com.example.buildingmanagement.controller;
//
//import com.example.buildingmanagement.entities.ControlGroupAccessControl;
//import com.example.buildingmanagement.service.ControlGroupAccessControlService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/control-group-access")
//public class ControlGroupAccessControlController {
//  @Autowired
//  private ControlGroupAccessControlService service;
//
//  @GetMapping
//  public List<ControlGroupAccessControl> getAll() {
//    return service.findAll();
//  }
//
//  @PostMapping
//  public ControlGroupAccessControl create(@RequestBody ControlGroupAccessControl controlGroupAccessControl) {
//    return service.save(controlGroupAccessControl);
//  }
//}
