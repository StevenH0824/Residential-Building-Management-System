package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.CardScannerRequestDTO;
import com.example.buildingmanagement.dtos.CardScannerResponseDTO;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.service.CardScannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/card-scanners")
public class CardScannerController {
  private final CardScannerService cardScannerService;

  public CardScannerController(CardScannerService cardScannerService) {
    this.cardScannerService = cardScannerService;
  }

  @GetMapping
  public List<CardScannerResponseDTO> getAllCardScanners() {
    List<CardScanner> cardScanners = cardScannerService.getAllCardScanners();
    return cardScanners.stream()
      .map(this::convertToResponseDTO)
      .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CardScannerResponseDTO> getCardScannerById(@PathVariable Long id) {
    Optional<CardScanner> cardScanner = cardScannerService.getCardScannerById(id);
    return cardScanner.map(this::convertToResponseDTO)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<CardScannerResponseDTO> createCardScanner(@RequestBody CardScannerRequestDTO request) {
    // Create a CardScanner entity from the request DTO
    CardScanner cardScanner = new CardScanner();
    cardScanner.setSerialNo(request.getSerialNo());
    cardScanner.setMake(request.getMake());
    cardScanner.setModel(request.getModel());

    // Save the card scanner and convert to response DTO
    CardScanner savedCardScanner = cardScannerService.createCardScanner(cardScanner);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDTO(savedCardScanner));
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

  private CardScannerResponseDTO convertToResponseDTO(CardScanner cardScanner) {
    return new CardScannerResponseDTO(
      cardScanner.getCardScannerId(),
      cardScanner.getSerialNo(),
      cardScanner.getMake(),
      cardScanner.getModel(),
      cardScanner.getAccessLogIds(),
      cardScanner.getAccessControlIds()
    );
  }
}
