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
public class AccessControlPersonDTO {
  private Long id;                   // ID of the access control person entry
  private Long accessControlId;      // ID of the associated access control
  private Long personId;             // ID of the associated person
  private LocalDate startDate;       // Start date of access
  private LocalDate endDate;         // End date of access
}
