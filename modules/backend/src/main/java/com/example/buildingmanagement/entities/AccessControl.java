package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashMap;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccessControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "card_scanner_id", nullable = false)
  private CardScan cardScan;
  @ElementCollection
  @MapKeyJoinColumn(name = "floor_id")
  @Column(name = "has_access")
  private HashMap<Long, Boolean> floorAccess;

}
