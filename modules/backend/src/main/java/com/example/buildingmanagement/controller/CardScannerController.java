package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.CardScannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card-scanners")
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

  @PostMapping("/{scannerId}/log-access")
  public ResponseEntity<Void> logAccess(@PathVariable Long scannerId, @RequestBody Person person) {
    cardScannerService.logAccess(scannerId, person);
    return ResponseEntity.ok().build();
  }

}
