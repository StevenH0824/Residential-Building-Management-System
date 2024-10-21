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
public class CardScanner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardScannerId;

  private String serialNo;
  private String make;
  private String model;

  @OneToMany(mappedBy = "cardScanner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<AccessLog> accessLogs;

  @OneToMany(mappedBy = "cardScanner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<AccessControl> accessControls;
}
