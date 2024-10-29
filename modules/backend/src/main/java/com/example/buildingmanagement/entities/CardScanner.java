package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "card_scanner") // Make sure this matches your DB table name
public class CardScanner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "scanner_id")
  private Long cardScannerId;

  @Column(name = "serial_no")
  private String serialNo;

  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;

  @OneToMany(mappedBy = "cardScanner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<AccessLog> accessLogs;

  @OneToMany(mappedBy = "cardScanner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<AccessControl> accessControls;

  public List<Long> getAccessLogIds() {
    return accessLogs.stream()
      .map(AccessLog::getId) // Assuming AccessLog has a method getId()
      .collect(Collectors.toList());
  }

  public List<Long> getAccessControlIds() {
    return accessControls.stream()
      .map(AccessControl::getId) // Assuming AccessControl has a method getId()
      .collect(Collectors.toList());
  }
}
