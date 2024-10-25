package com.example.buildingmanagement.dtos;

import com.example.buildingmanagement.entities.Floor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

  private Long roomId;
  private String number;
  private String description;
  private Floor floor;
}
