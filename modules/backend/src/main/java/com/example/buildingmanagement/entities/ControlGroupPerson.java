package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class ControlGroupPerson {

  @EmbeddedId // Using EmbeddedId for composite key
  private ControlGroupPersonId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("controlGroupId") // Maps the embedded ID to ControlGroup
  @JoinColumn(name = "control_group_id", referencedColumnName = "controlGroupId", nullable = false)
  private ControlGroup controlGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("personId") // Maps the embedded ID to Person
  @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false) // Ensure this matches the DB column
  private Person person;

  private LocalDate startDate;
  private LocalDate expirationDate;
}
