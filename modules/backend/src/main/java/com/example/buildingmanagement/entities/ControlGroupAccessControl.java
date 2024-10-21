package com.example.buildingmanagement.entities;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class ControlGroupAccessControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "control_group_id", nullable = false)
  private ControlGroup controlGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "access_control_id", nullable = false)
  private AccessControl accessControl;
}
