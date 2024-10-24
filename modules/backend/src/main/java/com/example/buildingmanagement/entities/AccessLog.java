package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class AccessLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "log_id")
  private Long accessLogId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scanner_id", nullable = false)
  private CardScanner cardScanner;

  //  @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "badge_id", nullable = false)
  //  private Badge badge;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @Column(name = "access_time", nullable = false)
  private LocalDateTime access_time;
}
