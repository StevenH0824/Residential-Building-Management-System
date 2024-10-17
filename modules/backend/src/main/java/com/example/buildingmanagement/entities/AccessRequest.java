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
public class AccessRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accessRequestId;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne
  @JoinColumn(name = "control_group_id", nullable = false)
  private ControlGroup controlGroup;

  @Enumerated(EnumType.STRING)
  private StatusType status;
  private LocalDateTime requestedDate;
  private LocalDateTime decisionDate;
  private String reason;
  private String processedBy;  // Admin or system processing the request
  @ManyToOne
  @JoinColumn(name = "floor_id")
  private Floor floor;

}
