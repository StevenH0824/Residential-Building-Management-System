package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Floor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long floorId;

  private String description;

  @ManyToOne
  @JoinColumn(name = "building_id", nullable = true)
  private Building building;

  @OneToMany(mappedBy = "floor")
  private List<Room> rooms;
}

