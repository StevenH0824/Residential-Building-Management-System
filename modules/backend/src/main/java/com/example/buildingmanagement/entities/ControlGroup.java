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

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @OneToMany(mappedBy = "controlGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Column(nullable = false)
  private List<ControlGroupAccessControl> controlGroupAccessControls;

  @OneToMany(mappedBy = "controlGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Corrected line
  @Column(nullable = false)
  private List<ControlGroupPerson> controlGroupPersons; // Optional: use plural for clarity
}




