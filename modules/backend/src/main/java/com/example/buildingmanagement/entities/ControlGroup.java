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
public class ControlGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;
  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;
  @ManyToOne
  @JoinColumn(name = "floor_id", nullable = false)
  private Floor floor;
}
