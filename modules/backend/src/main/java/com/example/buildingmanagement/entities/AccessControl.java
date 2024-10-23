package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "access_control") // Ensure this matches your DB table name
public class AccessControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "access_control_id")
  private Long accessControlId;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scanner_id")
  private CardScanner cardScanner;

  @OneToMany(mappedBy = "accessControl", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<AccessRequest> accessRequests; // Added for connection with AccessRequest

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;
}
