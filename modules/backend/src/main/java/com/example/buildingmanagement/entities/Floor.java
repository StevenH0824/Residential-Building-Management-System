package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Floor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long floorId;
  @ManyToOne
  @JoinColumn(name = "building_id", nullable = false)
  private Building building;
  private String description;
}
