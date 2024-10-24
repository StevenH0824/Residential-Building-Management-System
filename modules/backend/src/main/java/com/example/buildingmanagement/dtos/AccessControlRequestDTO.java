package com.example.buildingmanagement.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessControlRequestDTO {
  private Long accessControlId; // For updating existing AccessControl, otherwise it can be null for new creation
  @NotNull
  private String description;     // Description of the access control
  @NotNull
  private Long cardScannerId;    // ID of the associated card scanner (if needed)
  private Long controlGroupAccessControls;
  @NotNull
  private Long roomId;
}
