package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlGroupAccessControlDTO {
  private Long id;
  private Long personId;
  private Long controlGroupId;
  private Long accessControlId;
}
