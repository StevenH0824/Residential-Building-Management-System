package com.example.buildingmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Building {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long buildingId;
  private String name;
  private String address;
  private Integer totalFloors;
}

