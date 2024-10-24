package com.example.buildingmanagement.dtos;

import com.example.buildingmanagement.enums.StatusType;
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
  private Long maintenanceRequestId; // ID of the maintenance request
  private LocalDateTime createdDate;  // Creation date of the request
  private LocalDateTime endDate;      // End date of the maintenance task
  private String issue;                // Description of the issue
  private StatusType status;           // Status of the maintenance request
  private Long personId;               // ID of the associated person
  private Long roomId;


}
