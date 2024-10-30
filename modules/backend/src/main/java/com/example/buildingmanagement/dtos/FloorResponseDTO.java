package com.example.buildingmanagement.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class FloorResponseDTO {
  private Long floorId;
  private String number;
  private String description;
  private BuildingResponseDTO building;

  // Additional constructor to include building
  public FloorResponseDTO(Long floorId, String number, String description, BuildingResponseDTO building) {
    this.floorId = floorId;
    this.number = number;
    this.description = description;
    this.building = building;
  }

}
