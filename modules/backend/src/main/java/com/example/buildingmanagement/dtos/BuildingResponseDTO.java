package com.example.buildingmanagement.dtos;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingResponseDTO {
  private Long buildingId;
  private String name;
  private String address;
  private List<Long> floorIds; // Assuming you're returning the IDs of floors
}