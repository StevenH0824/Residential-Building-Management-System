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
@Table(name = "access_control")
public class AccessControl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "access_control_id")
  private Long accessControlId;

  @Column(name = "description")
  private String description;

//  // There is no direct relationship to Apt,SR, or F. area_id is refering to one of the
//  // entities without enforcing a foreign key constraint.
//  //reference one of the entities,
//  // you need to ensure that you manage the relationships in your application logic.
//  @Column(name = "area_id") // reference to either an Apartment, SpecialRoom, or Floor
//  private Long areaId;
//
//
//  @Enumerated(EnumType.STRING)
//  @Column(name = "area_type")
//  private AreaType areaType; // ENUM (Apt, SpecialRoom, Floor)



  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_scanner_id")
  private CardScanner cardScanner;

  @OneToMany(mappedBy = "accessControl", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ControlGroupAccessControl> controlGroupAccessControls;

  @ManyToOne
  @JoinColumn(name = "room_id", nullable = false)
  private Room room;
}



