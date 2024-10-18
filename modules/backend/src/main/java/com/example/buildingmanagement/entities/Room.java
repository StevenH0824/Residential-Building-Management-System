package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roomId;

  private String description;

  @ManyToOne
  @JoinColumn(name = "floor_id", nullable = true)
  private Floor floor;

  @OneToMany(mappedBy = "room")
  private List<CardScanner> cardScanners;

//  @OneToMany(mappedBy = "room")
//  private List<Allocation> allocations;
}
