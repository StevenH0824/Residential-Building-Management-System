package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlGroupAccessControlRequestDTO {
  private Long controlGroupId;     // ID of the Control Group
  private Long accessControlId;     // ID of the Access Control
}
