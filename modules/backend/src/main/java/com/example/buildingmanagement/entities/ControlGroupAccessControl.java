package com.example.buildingmanagement.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@IdClass(ControlGroupAccessControlId.class)
public class ControlGroupAccessControl implements Serializable {

  @Id
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "control_group_id", nullable = false)
  private ControlGroup controlGroup;

  @Id
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "access_control_id", nullable = false)
  private AccessControl accessControl;
}
