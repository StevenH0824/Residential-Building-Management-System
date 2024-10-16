package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CardScanner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardScannerId;
  private String description;
  @ManyToOne
  @JoinColumn(name = "floor_id", nullable = false)
  private Floor floor;
}
