package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccessGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accessGroupId;

  private String groupName;
  private String description;  // Defines the areas the group can access

  @ManyToMany
  @JoinTable(
    name = "group_person_access",  // Join table for people and access groups
    joinColumns = @JoinColumn(name = "access_group_id"),
    inverseJoinColumns = @JoinColumn(name = "person_id")
  )
  private Set<Person> members; // People who are part of this group

  @ManyToMany
  @JoinTable(
    name = "group_card_scanners",  // Join table for groups and card scanners
    joinColumns = @JoinColumn(name = "access_group_id"),
    inverseJoinColumns = @JoinColumn(name = "card_scanner_id")
  )
  private Set<CardScanner> cardScanners;  // Card scanners the group can access

  private LocalDateTime grantedDate;
  private LocalDateTime expirationDate; // Optional
}
