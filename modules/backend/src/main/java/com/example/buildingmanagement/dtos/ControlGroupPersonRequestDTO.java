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
public class ControlGroupPersonRequestDTO {
  private Long id;                         // ID of the control group person association
  private Long controlGroupId;             // ID of the associated control group
  private Long personId;                   // ID of the associated person
  private LocalDate startDate;             // Start date of the association
  private LocalDate expirationDate;        // Expiration date of the association
}
