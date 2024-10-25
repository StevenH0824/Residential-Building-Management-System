package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.ControlGroupPersonRequestDTO;
import com.example.buildingmanagement.entities.ControlGroupPerson;
import com.example.buildingmanagement.service.ControlGroupPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ControlGroupPerson")
public class ControlGroupPersonController {

  @Autowired
  private ControlGroupPersonService controlGroupPersonService;

    @PostMapping("/create")
  public ResponseEntity<ControlGroupPerson> getControlGroupPerson(@RequestBody ControlGroupPersonRequestDTO controlGroupPerson){
    ControlGroupPerson savedControlGroupPerson = controlGroupPersonService.createControlGroupPerson(controlGroupPerson);
    return  new ResponseEntity<>(savedControlGroupPerson, HttpStatus.CREATED);
    }


}
