package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MaintenanceRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean status;
  private String IssueDescription;

  @ManyToOne
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
  private LocalDateTime requestDate;
  private LocalDateTime resolvedDate;
}
