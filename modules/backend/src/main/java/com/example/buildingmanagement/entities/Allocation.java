package com.example.buildingmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Allocation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long allocationId;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;

  private LocalDateTime allocationDate; // Date of allocation
  private LocalDateTime expirationDate; // Optional, if you want to track lease terms
}
