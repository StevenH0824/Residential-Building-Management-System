package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApartmentDTO {
  private Long apartmentId;
  private String number;     // Apt number
  private String description;
  private Long floorId;      // ID of the associated floor (if needed)
}
