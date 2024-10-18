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
public class AccessLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accessLogId;
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
  @ManyToOne
  @JoinColumn(name = "cardScanner_id", nullable = false)
  private CardScanner cardScanner;
  private LocalDateTime accessTime;
}
