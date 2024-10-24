package com.example.buildingmanagement.DTOs;

import com.example.buildingmanagement.entities.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaintenanceRequestDTO {
  private Long id;
  private StatusType status;
  private String issueDescription;
  private Long apartmentId;
  private LocalDateTime requestDate;
  private LocalDateTime resolvedDate;
}