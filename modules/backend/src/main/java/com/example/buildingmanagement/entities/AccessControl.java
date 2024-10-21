package com.example.buildingmanagement.entities;

import com.example.buildingmanagement.enums.AreaType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "access_control")
public class AccessControl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "access_control_id")
  private Long accessControlId;

  @Column(name = "description")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "area_type")
  private AreaType areaType; // ENUM (Apt, SpecialRoom, Floor)

  @Column(name = "area_id") // reference to either an Apartment, SpecialRoom, or Floor
  private Long areaId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_scanner_id")
  private CardScanner cardScanner;

  @OneToMany(mappedBy = "accessControl", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ControlGroupAccessControl> controlGroupAccessControls;
}



