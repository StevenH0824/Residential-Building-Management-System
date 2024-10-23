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
public class AccessControlResponseDTO {
  private Long accessControlId;
  private String description;
  private Long cardScannerId;
  private String cardScannerModel;
  private Long roomId;
  private String roomNumber;
//  private List<ControlGroupAccessControlDTO> controlGroupAccessControls;
}


