package com.example.buildingmanagement.dtos;


import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomResponseDTO {
  private Long roomId;
  private String description;
  //  private String number;
  private Long floorId;

  public RoomResponseDTO(Long roomId, String number, String description, Floor floor) {
  }
}
