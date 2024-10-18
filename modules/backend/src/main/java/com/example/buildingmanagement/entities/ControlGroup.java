//package com.example.buildingmanagement.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Getter
//@Setter
//public class ControlGroup {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long controlGroupId;
//
//  private String groupName;
//  private String description;  // Defines the areas the group can access
//
//  // Define the relationship with AccessControl
//  @OneToMany(mappedBy = "controlGroup")
//  private Set<AccessControl> accessControls; // This will track people who are part of this control group
//
//  // Define the relationship with CardScanner
//  @ManyToMany
//  @JoinTable(
//    name = "control_group_scanners",  // Join table name
//    joinColumns = @JoinColumn(name = "control_group_id"),  // ControlGroup side
//    inverseJoinColumns = @JoinColumn(name = "card_scanner_id")  // CardScanner side
//  )
//  private Set<CardScanner> cardScanners;  // Card scanners (rooms) that the control group can access
//}
//
//
//
//
