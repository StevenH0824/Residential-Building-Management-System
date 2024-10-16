package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CardScan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  //  private Long personId;
  @ManyToOne
  @JoinColumn(name = "floor_id", nullable = false)
  private BuildingComponent buildingComponent;
}
