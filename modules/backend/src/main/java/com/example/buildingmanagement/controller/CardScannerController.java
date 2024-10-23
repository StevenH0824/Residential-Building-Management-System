package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.services.CardScannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class CardScannerController {
  private final CardScannerService cardScannerService;

  public CardScannerController(CardScannerService cardScannerService) {
    this.cardScannerService = cardScannerService;
  }

  @GetMapping
  public List<CardScanner> getAllCardScanners() {
    return cardScannerService.getAllCardScanners();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CardScanner> getCardScannerById(@PathVariable Long id) {
    Optional<CardScanner> cardScanner = cardScannerService.getCardScannerById(id);
    return cardScanner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public CardScanner createCardScanner(@RequestBody CardScanner cardScanner) {
    return cardScannerService.createCardScanner(cardScanner);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCardScanner(@PathVariable Long id) {
    cardScannerService.deleteCardScanner(id);
    return ResponseEntity.noContent().build();
  }
}
