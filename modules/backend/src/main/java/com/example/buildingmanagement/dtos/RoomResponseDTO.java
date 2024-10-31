package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomResponseDTO {
  private Long roomId;
  private String roomNumber;
  private String roomDescription;
  private Long floorId;
  private String floorDescription;
  private Long buildingId;
  private String buildingName;
  private String buildingAddress;


}
