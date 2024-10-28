package com.example.buildingmanagement.dtos;

import jakarta.validation.constraints.NotBlank;
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
  @NotBlank(message = "floor number is mandatory")
  private String number;
  private String description;
  private Long buildingId;
  private List<Long> roomIds; // If you want to include apartments
}
