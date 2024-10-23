package com.example.buildingmanagement.services;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.repositories.CardScannerRepository;

import java.util.List;
import java.util.Optional;

public class CardScannerService {
  private final CardScannerRepository cardScannerRepository;

  public CardScannerService(CardScannerRepository cardScannerRepository) {
    this.cardScannerRepository = cardScannerRepository;
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
}
