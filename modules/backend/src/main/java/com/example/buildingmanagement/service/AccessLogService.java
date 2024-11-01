package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.AccessLogRequestDTO;
import com.example.buildingmanagement.dtos.AccessLogResponseDTO;
import com.example.buildingmanagement.entities.AccessLog;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.AccessLogRepository;
import com.example.buildingmanagement.repository.CardScannerRepository;
import com.example.buildingmanagement.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessLogService {
  private final AccessLogRepository accessLogRepository;

  @Autowired
  public AccessLogService(AccessLogRepository accessLogRepository) {
    this.accessLogRepository = accessLogRepository;
  }

  @Autowired
  private CardScannerRepository cardScannerRepository;

  @Autowired
  private PersonRepository personRepository;


  public List<AccessLog> getAllAccessLogs() {
    return accessLogRepository.findAll(); // Fetch all access logs
  }

  public AccessLogResponseDTO createAccessLog(AccessLogRequestDTO requestDTO) {
    // Create a new AccessLog entity
    AccessLog accessLog = new AccessLog();

    // Set the cardScanner and person based on their IDs in the request
    CardScanner cardScanner = cardScannerRepository.findById(requestDTO.getCardScannerId())
      .orElseThrow(() -> new ResourceNotFoundException("CardScanner not found"));
    accessLog.setCardScanner(cardScanner);

    Person person = personRepository.findById(requestDTO.getPersonId())
      .orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    accessLog.setPerson(person);

    // Set the access time
    accessLog.setAccessTime(LocalDateTime.now()); // or use the timestamp from requestDTO if provided

    // Save the access log to the repository
    AccessLog savedLog = accessLogRepository.save(accessLog);

    // Create and return the response DTO
    AccessLogResponseDTO responseDTO = new AccessLogResponseDTO();
    responseDTO.setAccessLogId(savedLog.getAccessLogId());
    responseDTO.setCardScannerId(savedLog.getCardScanner().getId());
    responseDTO.setFloorDescription(savedLog.getCardScanner().getRoom().getFloor().getDescription());
    responseDTO.setRoomNumber(savedLog.getCardScanner().getRoom().getRoomNumber());
    responseDTO.setBuildingName(savedLog.getCardScanner().getRoom().getBuilding().getName());
    responseDTO.setAccessTime(savedLog.getAccessTime());
    responseDTO.setFullName(savedLog.getPerson().getFirstName() + " " + savedLog.getPerson().getLastName());

    return responseDTO;
  }


  public void deleteAccessLog(Long id) {
    accessLogRepository.deleteById(id); // Delete access log by ID
  }
}
