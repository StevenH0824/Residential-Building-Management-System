package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardScannerDTO {
  private Long cardScannerId; // ID of the card scanner
  private String serialNo;     // Serial number of the card scanner
  private String make;         // Make of the card scanner
  private String model;        // Model of the card scanner
  private List<Long> accessLogIds; // List of access log IDs (if needed)
  private List<Long> accessControlIds; // List of access control IDs (if needed)
}
