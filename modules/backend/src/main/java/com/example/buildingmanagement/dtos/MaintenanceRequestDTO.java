package com.example.buildingmanagement.dtos;

import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.enums.StatusType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MaintenanceRequestDTO {
  private LocalDateTime createdDate;
  private LocalDateTime endDate;
  private String issue;
  private StatusType status;
  private Long personId;
  private Long roomId;
}
