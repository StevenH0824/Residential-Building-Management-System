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
  @Enumerated(EnumType.STRING)
  private StatusType status;
  private String IssueDescription;
  @ManyToOne
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;
  private LocalDateTime requestDate;
  private LocalDateTime resolvedDate;
}
