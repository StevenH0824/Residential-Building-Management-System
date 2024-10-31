package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlGroupRequestDTO {
  private Long controlGroupId;
  private String name;
  private String description;
  private List<ControlGroupAccessControlDTO> accessControls;
}
