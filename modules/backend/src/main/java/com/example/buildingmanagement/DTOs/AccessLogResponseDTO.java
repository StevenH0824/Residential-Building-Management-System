package com.example.buildingmanagement.DTOs;

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
  private Long id;
  private Long cardScannerId;
  private Long personId;
  private LocalDateTime accessTime;
  //Request DTOs typically include only
  // the data needed from the client to perform an operation
  // (e.g., for creating or updating a resource). Output
}
