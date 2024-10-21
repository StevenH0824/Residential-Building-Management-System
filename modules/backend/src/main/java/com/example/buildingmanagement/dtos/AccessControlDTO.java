package com.example.buildingmanagement.dtos;

import com.example.buildingmanagement.enums.AreaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessControlDTO {
  private Long accessControlId; // ID of the access control
  private String description;     // Description of the access control
  private Long areaId;           // Reference to the associated Apartment, SpecialRoom, or Floor
  private AreaType areaType;     // Type of area (Apartment, SpecialRoom, Floor)
  private Long cardScannerId;    // ID of the associated card scanner (if needed)
}
