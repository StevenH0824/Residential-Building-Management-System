package com.example.buildingmanagement.service;


import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.repository.CardScannerRepository;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MaintenanceService {
  private final MaintenanceRequestRepository maintenanceRequestRepository;
  private final PersonRepository personRepository;
  private final RoomRepository roomRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public MaintenanceService(MaintenanceRequestRepository maintenanceRequestRepository, PersonRepository personRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
    this.maintenanceRequestRepository = maintenanceRequestRepository;
    this.personRepository = personRepository;
    this.roomRepository = roomRepository;
    this.modelMapper = modelMapper;
  }

  public List<MaintenanceResponseDTO> getAllMaintenanceRequests() {
    return maintenanceRequestRepository.findAll().stream()
      .map(this::convertToResponseDTO)
      .toList();
  }

  private MaintenanceResponseDTO convertToResponseDTO(MaintenanceRequest maintenanceRequest) {
    return new MaintenanceResponseDTO(
      maintenanceRequest.getMaintenanceRequestId(),
      maintenanceRequest.getCreatedDate(),
      maintenanceRequest.getEndDate(),
      maintenanceRequest.getIssue(),
      maintenanceRequest.getStatus(),
      maintenanceRequest.getPerson().getFirstName(),
      maintenanceRequest.getPerson().getLastName(),
      maintenanceRequest.getRoom().getNumber()
    );
  }

  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByPersonId(Long Id) {
    Person person = personRepository.findByPersonId(Id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByPerson(person);
    assert maintenanceEntity != null;
    return modelMapper.map(maintenanceEntity, MaintenanceResponseDTO.class); //mapping getMaintenanceRequestByPersonId to responseDTO using modelMapper.
  }


  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByMaintenanceId(Long Id) {
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByMaintenanceRequestId(Id);
    assert maintenanceEntity != null;
    return new MaintenanceResponseDTO(maintenanceEntity.getMaintenanceRequestId(),
      maintenanceEntity.getCreatedDate(),
      maintenanceEntity.getEndDate(),
      maintenanceEntity.getIssue(),
      maintenanceEntity.getStatus(),
      maintenanceEntity.getPerson().getFirstName(),
      maintenanceEntity.getPerson().getLastName(),
      maintenanceEntity.getRoom().getNumber()
    );
  }


  @Transactional
  public List<MaintenanceResponseDTO> getMaintenanceRequestByStatus(StatusType status) {
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
    return modelMapper.map(maintenanceEntity, MaintenanceResponseDTO.class);
  }

  @Transactional
  public Duration getAverageTimeToResolveIssue() {
    List<MaintenanceRequest> completedRequests = maintenanceRequestRepository.findByStatus(StatusType.DONE);

    if (completedRequests.isEmpty()) {
      return Duration.ZERO; // Or throw an exception if appropriate
    }

    long totalTime = 0;
    for (MaintenanceRequest request : completedRequests) {
      totalTime += ChronoUnit.SECONDS.between(request.getCreatedDate(), request.getEndDate());
    }

    return Duration.ofSeconds(totalTime / completedRequests.size());
  }

  @Transactional
  public Duration getAverageTimeToDenyIssue() {
    List<MaintenanceRequest> completedRequests = maintenanceRequestRepository.findByStatus(StatusType.DENIED);

    if (completedRequests.isEmpty()) {
      return Duration.ZERO; // Or throw an exception if appropriate
    }

    long totalTime = 0;
    for (MaintenanceRequest request : completedRequests) {
      totalTime += ChronoUnit.SECONDS.between(request.getCreatedDate(), request.getEndDate());
    }

    return Duration.ofSeconds(totalTime / completedRequests.size());
  }

}
