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
public class AccessLogDTO {
  private Long id;
  private Long scannerId;
  private Long personId;
  private LocalDateTime accessTime;

}
