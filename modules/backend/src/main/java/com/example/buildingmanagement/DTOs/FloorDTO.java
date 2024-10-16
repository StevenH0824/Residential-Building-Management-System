package com.example.buildingmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FloorDTO {
  private Long id;
  private Long buildingId;
  private Long floor_number;

}
