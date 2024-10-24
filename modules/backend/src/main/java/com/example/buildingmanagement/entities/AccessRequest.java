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
@Table(name = "access_request") // Ensure this matches your DB table name
public class AccessRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "access_control_id")
  private AccessControl accessControl;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person; // Changed from personId to person

  @Column(name = "request_time")
  private LocalDateTime requestTime;

  @Column(name = "approved")
  private boolean approved;


}
