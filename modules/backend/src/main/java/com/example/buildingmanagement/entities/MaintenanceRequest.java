package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MaintenanceRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long maintenanceRequestId;

  private LocalDateTime requestDate;
  private LocalDateTime resolvedDate;
  private String issueDescription;

  @Enumerated(EnumType.STRING)
  private StatusType status; // e.g., OPEN, IN_PROGRESS, RESOLVED

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;
}
