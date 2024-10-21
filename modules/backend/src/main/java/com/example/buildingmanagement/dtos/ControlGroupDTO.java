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
public class ControlGroupDTO {
  private Long controlGroupId;                      // ID of the control group
  private String name;                               // Name of the control group
  private String description;                        // Description of the control group
  private List<Long> controlGroupAccessControlIds;  // List of associated access control IDs
  private List<Long> controlGroupPersonIds;         // List of associated person IDs
}
