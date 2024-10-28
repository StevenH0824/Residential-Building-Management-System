package com.example.buildingmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.CardScannerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardScannerServiceTest {

    @InjectMocks
    private CardScannerService cardScannerService;

    @Mock
    private CardScannerRepository cardScannerRepository;

    @Mock
    private AccessLogService accessLogService;

    private CardScanner scanner;
    private CardScanner scanner1;
    private CardScanner scanner2;
    private Person person;

    @BeforeEach
    void setUp() {
      person = new Person();
      scanner = new CardScanner();
      scanner1 = new CardScanner();
      scanner2 = new CardScanner();
    }

    @Test
    void testGetAllCardScanners() {
      List<CardScanner> scanners = Arrays.asList(scanner1, scanner2);
      when(cardScannerRepository.findAll()).thenReturn(scanners);
      List<CardScanner> result = cardScannerService.getAllCardScanners();
      assertEquals(2, result.size());
      verify(cardScannerRepository, times(1)).findAll();
    }

    @Test
    void testGetCardScannerById() {
      Long id = 1L;
      when(cardScannerRepository.findById(id)).thenReturn(Optional.of(scanner));
      Optional<CardScanner> result = cardScannerService.getCardScannerById(id);
      assertTrue(result.isPresent());
      assertSame(scanner, result.get());
      verify(cardScannerRepository, times(1)).findById(id);
    }

    @Test
    void testCreateCardScanner() {
      when(cardScannerRepository.save(scanner)).thenReturn(scanner);
      CardScanner result = cardScannerService.createCardScanner(scanner);
      assertSame(scanner, result);
      verify(cardScannerRepository, times(1)).save(scanner);
    }

    @Test
    void testDeleteCardScanner() {
      Long id = 1L;
      cardScannerService.deleteCardScanner(id);
      verify(cardScannerRepository, times(1)).deleteById(id);
    }

    @Test
    void testLogAccess() {
      Long scannerId = 1L;
      when(cardScannerRepository.findById(scannerId)).thenReturn(Optional.of(scanner));
      cardScannerService.logAccess(scannerId, person);
      verify(accessLogService, times(1)).createAccessLog(scanner, person);
    }

    @Test
    void testLogAccess_ScannerNotFound() {
      Long scannerId = 1L;
      when(cardScannerRepository.findById(scannerId)).thenReturn(Optional.empty());
      RuntimeException thrown = assertThrows(RuntimeException.class, () -> cardScannerService.logAccess(scannerId, person));
      assertEquals("CardScanner not found", thrown.getMessage());
    }
  }

