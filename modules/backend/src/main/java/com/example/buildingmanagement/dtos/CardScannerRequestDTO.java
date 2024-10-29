package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardScannerRequestDTO {
  private String serialNo; // Serial number of the card scanner
  private String make;     // Make of the card scanner
  private String model;    // Model of the card scanner
}
