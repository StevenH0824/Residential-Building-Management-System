
package com.example.buildingmanagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomRequestDTO {
  private Long roomId;
  private String number;
  private String description;
  private Long floorId;

}
