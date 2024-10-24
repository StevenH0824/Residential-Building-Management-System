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
import java.util.ArrayList;
import java.util.List;
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
  ModelMapper modelMapper;


//  @Transactional
//  public MaintenanceRequestDTO getMaintenanceRequestByPersonId(Long Id) {
//    Person person = new Person(jon, );
//    MaintenanceRequestRepository.save(person);
//    return MaintenanceResponseDTO.;
//  }
//

  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByPersonId(Long Id) {
    Person person = personRepository.findByPersonId(Id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByPerson(person);
    assert maintenanceEntity != null;
//    return new MaintenanceResponseDTO(maintenanceEntity.getMaintenanceRequestId(), maintenanceEntity.getCreatedDate(), maintenanceEntity.getEndDate(),maintenanceEntity.getIssue(), maintenanceEntity.getStatus(), maintenanceEntity.getPerson().getFirstName(),
//      maintenanceEntity.getPerson().getLastName(),maintenanceEntity.getRoom().getNumber()
//    );
    return modelMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
  }



  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByMaintenanceId(Long Id) {
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByMaintenanceRequestId(Id);
    assert maintenanceEntity != null;
    return new MaintenanceResponseDTO(maintenanceEntity.getMaintenanceRequestId(), maintenanceEntity.getCreatedDate(), maintenanceEntity.getEndDate(), maintenanceEntity.getIssue(), maintenanceEntity.getStatus(), maintenanceEntity.getPerson().getFirstName(),
      maintenanceEntity.getPerson().getLastName(), maintenanceEntity.getRoom().getNumber()
    );
  }


  @Transactional
  public List<MaintenanceResponseDTO> getMaintenanceRequestByStatus(StatusType status){
    List<MaintenanceRequest> maintenanceEntity = maintenanceRequestRepository.findByStatus(status);
    assert maintenanceEntity != null;
    List<MaintenanceResponseDTO> responseDTOs = new ArrayList<>();
    for (MaintenanceRequest entity : maintenanceEntity) {
      MaintenanceResponseDTO dto = new MaintenanceResponseDTO(
        entity.getMaintenanceRequestId(),
        entity.getCreatedDate(),
        entity.getEndDate(),
        entity.getIssue(),
        entity.getStatus(),
        entity.getPerson().getFirstName(),
        entity.getPerson().getLastName(),
        entity.getRoom().getNumber()
      );
      responseDTOs.add(dto);
    }

    return responseDTOs;



  }




  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByRoomId(Long Id) {
    Room room = roomRepository.findByRoomId(Id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByRoom(room);
    assert maintenanceEntity != null;
//    return new MaintenanceResponseDTO(maintenanceEntity.getMaintenanceRequestId(), maintenanceEntity.getCreatedDate(), maintenanceEntity.getEndDate(),maintenanceEntity.getIssue(), maintenanceEntity.getStatus(), maintenanceEntity.getPerson().getFirstName(),
//      maintenanceEntity.getPerson().getLastName(),maintenanceEntity.getRoom().getNumber()
//    );
    return modelMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
  }

//  @Transactional
//  public List<MaintenanceResponseDTO> getMaintenanceRequestByCreatedDate(LocalDateTime createdDate){
//    List<MaintenanceRequest> maintenanceEntity = maintenanceRequestRepository.findByCreatedDate(createdDate);
//    assert maintenanceEntity != null;
//    List<MaintenanceResponseDTO> responseDTOs = new ArrayList<>();
//    for (MaintenanceRequest entity : maintenanceEntity) {
//      MaintenanceResponseDTO dto = new MaintenanceResponseDTO(
//        entity.getMaintenanceRequestId(),
//        entity.getCreatedDate(),
//        entity.getEndDate(),
//        entity.getIssue(),
//        entity.getStatus(),
//        entity.getPerson().getFirstName(),
//        entity.getPerson().getLastName(),
//        entity.getRoom().getNumber()
//      );
//      responseDTOs.add(dto);
//    }
//
//    return responseDTOs;
//
//
//
//  }
//
//  @Transactional
//  public List<MaintenanceResponseDTO> getMaintenanceRequestByEndDate(LocalDateTime endDate){
//    List<MaintenanceRequest> maintenanceEntity = maintenanceRequestRepository.findByEndDate(endDate);
//    assert maintenanceEntity != null;
//    List<MaintenanceResponseDTO> responseDTOs = new ArrayList<>();
//    for (MaintenanceRequest entity : maintenanceEntity) {
//      MaintenanceResponseDTO dto = new MaintenanceResponseDTO(
//        entity.getMaintenanceRequestId(),
//        entity.getCreatedDate(),
//        entity.getEndDate(),
//        entity.getIssue(),
//        entity.getStatus(),
//        entity.getPerson().getFirstName(),
//        entity.getPerson().getLastName(),
//        entity.getRoom().getNumber()
//      );
//      responseDTOs.add(dto);
//    }
//
//    return responseDTOs;
//
//
//
//  }










}





//
//  public MaintenanceResponseDTO getMaintenanceRequestById(Long Id) {
//    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByMaintenanceRequestId(Id);
//    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
//
//  }
//
//
////  public MaintenanceResponseDTO getMaintenanceRequestByPersonId(Long Id) {
//////    Person person = maintenanceRequestRepository.findByPerson(Id);
////    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByPerson(Id);
//////  return maintenanceEntity..map(maintenanceEntity, MaintenanceResponseDTO.class);
////        return new MaintenanceResponseDTO(firstName =  maintenanceEntity.getPerson().getFirstName(),
////       maintenanceEntity.getPerson().getLastName());
////  }
//
//  public MaintenanceResponseDTO findByRoom(Room Id) {
//    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByRoom(Id);
//    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
//  }
//
//  public MaintenanceResponseDTO findByStatus(StatusType status) {
//    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByStatus(status);
//    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
//
//  }
//
//  public MaintenanceResponseDTO findByRequestDate(LocalDateTime requestDate) {
//    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByCreatedDate(requestDate);
//    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
//
//  }
//
//  public MaintenanceResponseDTO findByResolvedDate(LocalDateTime resolvedDate) {
//    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByEndDate(resolvedDate);
//    return MaintenanceRequestMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
//  }

//  public List<MaintenanceRequestDTO> findAllRequests(){
//    List<MaintenanceRequest> RequestList = maintenanceRequestRepository.findAllRequests();
//
//    return RequestList.stream().map(maintenanceRequest -> MaintenanceRequestMapper.
//      map(maintenanceRequest, MaintenanceRequestDTO.class)).collect(Collectors.toList());
//  }
