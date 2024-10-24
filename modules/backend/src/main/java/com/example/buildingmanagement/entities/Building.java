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
public class Building {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long buildingId;
  private String name;
  private String address;
  @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Floor> floors;
}
