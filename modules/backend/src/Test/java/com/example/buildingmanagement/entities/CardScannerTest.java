package com.example.buildingmanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class CardScannerTest {
  CardScanner cardScanner;

  @BeforeEach
  void setUp() {
    cardScanner = new CardScanner();
  }

  @Test
  void getCardScannerId() {
    cardScanner.setCardScannerId(1L);
    assertEquals(1L, cardScanner.getCardScannerId());
  }

  @Test
  void getSerialNo() {
    String serialNumber = "123";
    cardScanner.setSerialNo(serialNumber);
    assertEquals(serialNumber, cardScanner.getSerialNo());
  }

  @Test
  void getMake() {
    String make = "bestMake";
    cardScanner.setMake(make);
    assertEquals(make, cardScanner.getMake());
  }

  @Test
  void getModel() {
    String model = "bestModel";
    cardScanner.setModel(model);
    assertEquals(model, cardScanner.getModel());
  }

  @Test
  void getAccessLogs() {
    AccessLog accessLog = mock(AccessLog.class);
    List<AccessLog> accessLogs = new ArrayList<>();
    accessLogs.add(accessLog);
    cardScanner.setAccessLogs(accessLogs);
  }

  @Test
  void getAccessControls() {
    AccessControl accessControl = mock(AccessControl.class);
    List<AccessControl> accessControls = new ArrayList<>();
    accessControls.add(accessControl);
    cardScanner.setAccessControls(accessControls);
    assertNotNull(cardScanner.getAccessControls());
    assertEquals(1, cardScanner.getAccessControls().size());
    assertSame(accessControl, cardScanner.getAccessControls().get(0));
  }
}
