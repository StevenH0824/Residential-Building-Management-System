package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessRequestDTO {
  private Long id;                     // ID of the access request
  private Long accessControlId;        // ID of the associated access control
  private Long personId;               // ID of the associated person
  private LocalDateTime requestTime;   // Time of the request
  private boolean approved;             // Approval status of the request
}
