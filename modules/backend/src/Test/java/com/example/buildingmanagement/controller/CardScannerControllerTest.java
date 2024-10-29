package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person; // Adjust as necessary
import com.example.buildingmanagement.service.CardScannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CardScannerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private CardScannerService cardScannerService;

  @InjectMocks
  private CardScannerController cardScannerController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(cardScannerController).build();
  }

  @Test
  void testGetAllCardScanners() throws Exception {
    CardScanner scanner1 = new CardScanner(1L, "SN123", "Make1", "Model1", null, null);
    CardScanner scanner2 = new CardScanner(2L, "SN456", "Make2", "Model2", null, null);

    when(cardScannerService.getAllCardScanners()).thenReturn(Arrays.asList(scanner1, scanner2));

    mockMvc.perform(get("/card-scanners"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].cardScannerId").value(1))
      .andExpect(jsonPath("$[0].serialNo").value("SN123"))
      .andExpect(jsonPath("$[0].make").value("Make1"))
      .andExpect(jsonPath("$[0].model").value("Model1"))
      .andExpect(jsonPath("$[1].cardScannerId").value(2))
      .andExpect(jsonPath("$[1].serialNo").value("SN456"))
      .andExpect(jsonPath("$[1].make").value("Make2"))
      .andExpect(jsonPath("$[1].model").value("Model2"));

    verify(cardScannerService, times(1)).getAllCardScanners();
  }

  @Test
  void testGetCardScannerById() throws Exception {
    CardScanner scanner = new CardScanner(1L, "SN123", "Make1", "Model1", null, null);

    when(cardScannerService.getCardScannerById(1L)).thenReturn(Optional.of(scanner));

    mockMvc.perform(get("/card-scanners/{id}", 1L))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.cardScannerId").value(1))
      .andExpect(jsonPath("$.serialNo").value("SN123"))
      .andExpect(jsonPath("$.make").value("Make1"))
      .andExpect(jsonPath("$.model").value("Model1"));

    verify(cardScannerService, times(1)).getCardScannerById(1L);
  }

  @Test
  void testGetCardScannerById_NotFound() throws Exception {
    when(cardScannerService.getCardScannerById(1L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/card-scanners/{id}", 1L))
      .andExpect(status().isNotFound());

    verify(cardScannerService, times(1)).getCardScannerById(1L);
  }

  @Test
  void testCreateCardScanner() throws Exception {
    CardScanner newScanner = new CardScanner(null, "SN789", "Make3", "Model3", null, null);
    CardScanner createdScanner = new CardScanner(1L, "SN789", "Make3", "Model3", null, null);

    when(cardScannerService.createCardScanner(any(CardScanner.class))).thenReturn(createdScanner);

    mockMvc.perform(post("/card-scanners")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"serialNo\":\"SN789\",\"make\":\"Make3\",\"model\":\"Model3\"}"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.cardScannerId").value(1))
      .andExpect(jsonPath("$.serialNo").value("SN789"))
      .andExpect(jsonPath("$.make").value("Make3"))
      .andExpect(jsonPath("$.model").value("Model3"));

    verify(cardScannerService, times(1)).createCardScanner(any(CardScanner.class));
  }

  @Test
  void testDeleteCardScanner() throws Exception {
    doNothing().when(cardScannerService).deleteCardScanner(1L);

    mockMvc.perform(delete("/card-scanners/{id}", 1L))
      .andExpect(status().isNoContent());

    verify(cardScannerService, times(1)).deleteCardScanner(1L);
  }

//  @Test
//  void testLogAccess() throws Exception {
//    Person person = new Person(2L, "john.doe@example.com", "John", "Doe", "123456"); // Adjust if necessary
//
//    doNothing().when(cardScannerService).logAccess(2L, person);
//
//    mockMvc.perform(post("/card-scanners/{scannerId}/log-access", 2L)
//        .contentType(MediaType.APPLICATION_JSON)
//        .content("{\"id\":2,\"email\":\"john.doe@example.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"phone\":\"123456\"}"))
//      .andExpect(status().isOk());
//
//    verify(cardScannerService, times(1)).logAccess(2L, person);
//  }
}
