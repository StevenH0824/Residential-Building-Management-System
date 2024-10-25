package com.example.buildingmanagement.dtos;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingRequestDTO {
  private String name;
  private String address;
  private List<Long> floorIds;
}
