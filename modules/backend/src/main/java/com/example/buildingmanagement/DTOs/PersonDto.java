package com.example.buildingmanagement.DTOs;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonDto {
  private Long id;
  private String full_name;
  private Long controlGroupId;
}
