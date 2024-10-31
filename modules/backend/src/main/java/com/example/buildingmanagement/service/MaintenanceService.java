package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.MaintenanceRequestDTO;
import com.example.buildingmanagement.dtos.MaintenanceResponseDTO;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import com.example.buildingmanagement.repository.PersonRepository;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.MaintenanceRequest;
import com.example.buildingmanagement.repository.MaintenanceRequestRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MaintenanceService {
  private final MaintenanceRequestRepository maintenanceRequestRepository;
  private final PersonRepository personRepository;
  private final RoomRepository roomRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public MaintenanceService(MaintenanceRequestRepository maintenanceRequestRepository,
                            PersonRepository personRepository,
                            RoomRepository roomRepository,
                            ModelMapper modelMapper) {
    this.maintenanceRequestRepository = maintenanceRequestRepository;
    this.personRepository = personRepository;
    this.roomRepository = roomRepository;
    this.modelMapper = modelMapper;
  }

  public List<MaintenanceResponseDTO> getAllMaintenanceRequests() {
    List<MaintenanceRequest> requests = maintenanceRequestRepository.findAll();
    return requests.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  private MaintenanceResponseDTO convertToResponseDTO(MaintenanceRequest request) {
    return new MaintenanceResponseDTO(
      request.getMaintenanceRequestId(),
      request.getCreatedDate(),
      request.getEndDate(),
      request.getIssue(),
      request.getStatus().name(),
      request.getRoom().getNumber(),
      request.getRoom().getFloor().getDescription(),
      request.getRoom().getFloor().getBuilding().getName(),
      request.getPerson().getFirstName() + " " + request.getPerson().getLastName(),
      request.getPerson().getPhoneNumber(),
      request.getPerson().getPersonId(),
      request.getRoom().getRoomId()
    );
  }


  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByPersonId(Long id) {
    Person person = personRepository.findByPersonId(id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByPerson(person);
    return convertToResponseDTO(maintenanceEntity);
  }

  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByMaintenanceId(Long id) {
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByMaintenanceRequestId(id);
    return convertToResponseDTO(maintenanceEntity);
  }

  @Transactional
  public List<MaintenanceResponseDTO> getMaintenanceRequestByStatus(StatusType status) {
    List<MaintenanceRequest> maintenanceEntities = maintenanceRequestRepository.findByStatus(status);
    return maintenanceEntities.stream().map(this::convertToResponseDTO).collect(Collectors.toList());
  }

  @Transactional
  public MaintenanceResponseDTO getMaintenanceRequestByRoomId(Long id) {
    Room room = roomRepository.findByRoomId(id);
    MaintenanceRequest maintenanceEntity = maintenanceRequestRepository.findByRoom(room);
    return convertToResponseDTO(maintenanceEntity);
  }

  @Transactional
  public Duration getAverageTimeToResolveIssue() {
    List<MaintenanceRequest> completedRequests = maintenanceRequestRepository.findByStatus(StatusType.DONE);
    return calculateAverageTime(completedRequests);
  }

  @Transactional
  public Duration getAverageTimeToDenyIssue() {
    List<MaintenanceRequest> deniedRequests = maintenanceRequestRepository.findByStatus(StatusType.DENIED);
    return calculateAverageTime(deniedRequests);
  }

  private Duration calculateAverageTime(List<MaintenanceRequest> requests) {
    if (requests.isEmpty()) {
      return Duration.ZERO;
    }

    long totalTime = requests.stream()
      .mapToLong(request -> ChronoUnit.SECONDS.between(request.getCreatedDate(), request.getEndDate()))
      .sum();

    return Duration.ofSeconds(totalTime / requests.size());
  }

  public MaintenanceRequest createMaintenanceRequest(MaintenanceRequestDTO dto) {
    Person person = personRepository.findById(dto.getPersonId())
      .orElseThrow(() -> new RuntimeException("Person not found"));
    Room room = roomRepository.findById(dto.getRoomId())
      .orElseThrow(() -> new RuntimeException("Room not found"));

    MaintenanceRequest request = new MaintenanceRequest();
    request.setCreatedDate(dto.getCreatedDate());
    request.setEndDate(dto.getEndDate());
    request.setIssue(dto.getIssue());
    request.setStatus(dto.getStatus());
    request.setPerson(person);
    request.setRoom(room);

    return maintenanceRequestRepository.save(request);  }
}

