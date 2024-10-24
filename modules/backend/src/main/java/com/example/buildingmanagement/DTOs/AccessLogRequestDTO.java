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

public class AccessLogRequestDTO {
  private Long id;
  private Long cardScannerId;
  private Long personId;
  private LocalDateTime accessTime;
//Response DTOs are shaped according to what needs to be returned to the client,
// which can include additional fields or omit fields for security or performance reasons. Input



}
