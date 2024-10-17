package com.example.buildingmanagement.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardScanDTO {
  private Long id;
  private Long floorId;
  private String description;
}
