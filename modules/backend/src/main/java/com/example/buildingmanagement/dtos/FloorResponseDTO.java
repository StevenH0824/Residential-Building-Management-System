package com.example.buildingmanagement.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class FloorResponseDTO {
  private Long floorId;
  private String number;
  private String description;
  private BuildingDTO building;
  private String roomNumber;
  private String roomDescription;

  // Additional constructor to include building
  public FloorResponseDTO(Long floorId, String number, String description, BuildingDTO building, String roomNumber,String roomDescription) {
    this.floorId = floorId;
    this.number = number;
    this.description = description;
    this.building = building;
    this.roomNumber = roomNumber;
    this.roomDescription = roomDescription;
  }

}
