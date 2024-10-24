package com.example.buildingmanagement.dtos;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingRequestDTO {
  private Long buildingId;
  private String name;
  private String address;
  private List<Long> floorIds; // Assuming you might want to reference floors by their IDs
}
