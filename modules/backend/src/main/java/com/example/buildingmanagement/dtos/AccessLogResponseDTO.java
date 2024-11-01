package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessLogResponseDTO {
  private Long accessLogId;
  private Long cardScannerId;
  private String floorDescription;
  private String roomNumber;
  private String buildingName;
  private LocalDateTime accessTime;
  private String fullName;
}
