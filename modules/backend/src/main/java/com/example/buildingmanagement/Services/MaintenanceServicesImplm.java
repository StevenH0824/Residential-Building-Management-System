package com.example.buildingmanagement.Services;


import com.example.buildingmanagement.Repositories.MaintenanceRepository;
import com.example.buildingmanagement.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServicesImplm implements MaintenanceServices{

  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private MaintenanceRepository maintenanceRepository;



}
