package com.example.buildingmanagement.entities;

import com.example.buildingmanagement.enums.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class MaintenanceRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long maintenanceRequestId;

  @Column(name = "created_date")
  private LocalDateTime created_date;
  @Column(name = "end_date")
  private LocalDateTime end_date;
  @Column(name = "issue")
  private String issue;
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusType status;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;
}
