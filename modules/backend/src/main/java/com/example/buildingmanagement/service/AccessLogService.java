package com.example.buildingmanagement.service;


import com.example.buildingmanagement.entities.AccessLog;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.AccessLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccessLogService {
  private final AccessLogRepository accessLogRepository;

  public AccessLogService(AccessLogRepository accessLogRepository) {
    this.accessLogRepository = accessLogRepository;
  }

  public AccessLog createAccessLog(CardScanner cardScanner, Person person) {
    AccessLog accessLog = new AccessLog();
    accessLog.setAccess_time(LocalDateTime.now());
    accessLog.setCardScanner(cardScanner);
    accessLog.setPerson(person);  // Assuming you're fetching the person who scanned the card
    return accessLogRepository.save(accessLog);
  }
}
