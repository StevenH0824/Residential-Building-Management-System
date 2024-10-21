package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repositories.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {
  @Autowired
  private MaintenanceRequestRepository maintenanceRequestRepository;

  // Provide methods here for solving different maintenance issues
}
