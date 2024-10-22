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
public class AccessLogDTO {
  private Long accessLogId;          // ID of the access log
  private Long cardScannerId;        // ID of the associated card scanner
  private Long badgeId;              // ID of the associated badge
  private LocalDateTime timestamp;   // Timestamp of the access event
}
