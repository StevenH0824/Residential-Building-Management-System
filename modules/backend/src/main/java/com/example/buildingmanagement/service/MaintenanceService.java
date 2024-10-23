package com.example.buildingmanagement.service;


import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.repository.PersonRepository;
import com.example.buildingmanagement.dtos.MaintenanceRequestDTO;
//import com.example.buildingmanagement.entities.MaintenanceRequest;

import com.example.buildingmanagement.entities.Person;

import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.repository.MaintenanceRequestRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class MaintenanceService {
  @Autowired
  private MaintenanceRequestRepository maintenanceRequestRepository;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private MaintenanceRequestDTO maintenanceRequestDTO;
  @Autowired
  ModelMapper MaintenanceRequestMapper;


  public MaintenanceResponseDTO getMaintenanceRequestById(Long Id) {
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByMaintenanceRequestId(Id);
    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);

  }


  public MaintenanceResponseDTO getByPerson(Person Id) {
//    Person person = maintenanceRequestRepository.findByPerson(Id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByPerson(Id);
  return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
    //    return new MaintenanceResponseDTO(firstName =  maintenanceEntity.getPerson().getFirstName(),
//       maintenanceEntity.getPerson().getLastName());
  }

  public MaintenanceResponseDTO findByRoom(Room Id){
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByRoom(Id);
    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
  }

  public MaintenanceResponseDTO findByStatus(StatusType status){
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByStatus(status);
    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);

  }

  public MaintenanceResponseDTO findByRequestDate(LocalDateTime requestDate){
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByCreatedDate(requestDate);
    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);

  }

  public MaintenanceResponseDTO findByResolvedDate(LocalDateTime resolvedDate){
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByEndDate(resolvedDate);
    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
  }

//  public List<MaintenanceRequestDTO> findAllRequests(){
//    List<MaintenanceRequest> RequestList = maintenanceRequestRepository.findAllRequests();
//
//    return RequestList.stream().map(maintenanceRequest -> MaintenanceRequestMapper.
//      map(maintenanceRequest, MaintenanceRequestDTO.class)).collect(Collectors.toList());
//  }


}

