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
public class FloorDTO {
  private Long floorId;
  private Long buildingId;
  private String description;
  private String number; // Floor Number
//  private List<RoomDTO> apartments; // If you want to include apartments
}