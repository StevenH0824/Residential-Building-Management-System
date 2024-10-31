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
public class MaintenanceResponseDTO {
  private Long maintenanceRequestId;
  private LocalDateTime createdDate;
  private LocalDateTime endDate;
  private String issue;
  private String status;
  private String roomNumber;
  private String floorDescription;
  private String buildingName;
  private String personFullName;
  private String phoneNumber;
  private Long personId;
  private Long roomId;
}
