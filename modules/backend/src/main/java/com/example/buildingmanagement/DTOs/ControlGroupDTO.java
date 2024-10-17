package com.example.buildingmanagement.DTOs;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ControlGroupDTO {
  private Long id;
  private String groupName;
  private String description;
}
