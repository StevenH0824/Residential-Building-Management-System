package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialRoomDTO {
  private Long specialRoomId;
  private String name;
  private String purpose;
  private String description;
  private FloorDTO floor; // Include floor details if necessary
}
