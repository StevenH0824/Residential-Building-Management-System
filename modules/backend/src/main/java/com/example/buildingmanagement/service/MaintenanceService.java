package com.example.buildingmanagement.service;

import com.example.buildingmanagement.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {
  @Autowired
  private MaintenanceRequestRepository maintenanceRequestRepository;

  // Provide methods here for solving different maintenance issues
}
