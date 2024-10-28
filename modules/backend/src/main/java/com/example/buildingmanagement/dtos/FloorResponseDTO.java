package com.example.buildingmanagement.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FloorResponseDTO {
  private Long floorId;
  private String number;
  private String description;
}
