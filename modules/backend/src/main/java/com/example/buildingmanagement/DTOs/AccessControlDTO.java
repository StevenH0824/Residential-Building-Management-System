package com.example.buildingmanagement.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessControlDTO {
  private Long id;
  private Long controlGroupId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long apartmentId;
  private Long personId;
}
