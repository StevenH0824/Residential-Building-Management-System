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
public class ControlGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long controlGroupId;

  private String name;
  private String description;

  @OneToMany(mappedBy = "controlGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ControlGroupAccessControl> controlGroupAccessControls;

  @OneToMany(mappedBy = "controlGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Corrected line
  private List<ControlGroupPerson> controlGroupPersons; // Optional: use plural for clarity
}




