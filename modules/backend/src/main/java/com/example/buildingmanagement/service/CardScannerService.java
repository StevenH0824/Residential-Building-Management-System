package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.CardScannerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardScannerService {
  private final CardScannerRepository cardScannerRepository;
  private final AccessLogService accessLogService;


  public CardScannerService(CardScannerRepository cardScannerRepository, AccessLogService accessLogService) {
    this.cardScannerRepository = cardScannerRepository;
    this.accessLogService = accessLogService;
  }

  public List<CardScanner> getAllCardScanners() {
    return cardScannerRepository.findAll();
  }

  public Optional<CardScanner> getCardScannerById(Long id) {
    return cardScannerRepository.findById(id);
  }

  public CardScanner createCardScanner(CardScanner cardScanner) {
    return cardScannerRepository.save(cardScanner);
  }

  public void deleteCardScanner(Long id) {
    cardScannerRepository.deleteById(id);
  }

  public void logAccess(Long scannerId, Person person) {
    CardScanner cardScanner = cardScannerRepository.findById(scannerId)
      .orElseThrow(() -> new RuntimeException("CardScanner not found"));

    accessLogService.createAccessLog(cardScanner, person);  // Log the access
  }
}
