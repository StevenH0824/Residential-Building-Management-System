package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlGroupPersonResponseDTO {
  private Long controlGroupId;  // ID of the Control Group
  private Long personId;        // ID of the Person
  private LocalDate startDate;  // Start date of the association
  private LocalDate expirationDate; // Expiration date of the association
}
