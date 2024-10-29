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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessLogService {
  private final AccessLogRepository accessLogRepository;
  private final CardScannerRepository cardScannerRepository;
  private final PersonRepository personRepository;

  public AccessLogService(AccessLogRepository accessLogRepository,
                          CardScannerRepository cardScannerRepository,
                          PersonRepository personRepository) {
    this.accessLogRepository = accessLogRepository;
    this.cardScannerRepository = cardScannerRepository;
    this.personRepository = personRepository;
  }

  @Transactional
  public AccessLogResponseDTO logAccess(AccessLogRequestDTO accessLogRequestDTO) {
    // Fetch associated entities
    CardScanner cardScanner = cardScannerRepository.findById(accessLogRequestDTO.getCardScannerId())
      .orElseThrow(() -> new RuntimeException("CardScanner not found"));

    Person person = personRepository.findById(accessLogRequestDTO.getBadgeId())
      .orElseThrow(() -> new RuntimeException("Person not found"));

    // Create and populate AccessLog entity
    AccessLog accessLog = new AccessLog();
    accessLog.setAccessTime(accessLogRequestDTO.getTimestamp());
    accessLog.setCardScanner(cardScanner);
    accessLog.setPerson(person);

    // Save the access log to the repository
    accessLog = accessLogRepository.save(accessLog);

    // Create and return the response DTO
    return new AccessLogResponseDTO(
      accessLog.getAccessLogId(),
      cardScanner.getCardScannerId(),
      person.getPersonId(),
      accessLog.getAccessTime()
    );
  }

  public List<AccessLogResponseDTO> getAllAccessLogs() {
    return accessLogRepository.findAll().stream()
      .map(log -> new AccessLogResponseDTO(
        log.getAccessLogId(),
        log.getCardScanner().getCardScannerId(),
        log.getPerson().getPersonId(),
        log.getAccessTime()
      ))
      .collect(Collectors.toList()); // Fetch all access logs and convert to response DTOs
  }

  public void deleteAccessLog(Long id) {
    accessLogRepository.deleteById(id); // Delete access log by ID
  }

  public void createAccessLog(CardScanner cardScanner, Person person) {
    // Create and populate AccessLog entity
    AccessLog accessLog = new AccessLog();
    accessLog.setAccessTime(LocalDateTime.now()); // Set the current timestamp
    accessLog.setCardScanner(cardScanner); // Set the associated CardScanner
    accessLog.setPerson(person); // Set the associated Person

    // Save the access log to the repository
    accessLogRepository.save(accessLog);
  }
}
