package com.example.buildingmanagement.services;

import com.example.buildingmanagement.repositories.ControlGroupPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlGroupPersonService {
  @Autowired
  private ControlGroupPersonRepository controlGroupPerson;
}
