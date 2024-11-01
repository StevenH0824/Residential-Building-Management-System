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
public class AccessLogRequestDTO {
  private Long cardScannerId; // ID of the associated card scanner
  @Getter
  private Long personId;       // ID of the associated badge (Person ID)
  private LocalDateTime timestamp; // Timestamp of the access event
}
