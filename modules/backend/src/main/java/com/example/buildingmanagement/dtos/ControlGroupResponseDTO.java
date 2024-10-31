package com.example.buildingmanagement.dtos;

import java.util.List;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ControlGroupResponseDTO {
  private Long controlGroupId;
  private String name;
  private String description;
  private List<ControlGroupAccessControlDTO> accessControls;
}
