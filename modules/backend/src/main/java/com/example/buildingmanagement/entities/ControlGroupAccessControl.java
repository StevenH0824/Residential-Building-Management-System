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

  @EmbeddedId
  private ControlGroupAccessControlId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("controlGroupId") // Maps the embedded ID to ControlGroup
  @JoinColumn(name = "control_group_id", referencedColumnName = "controlGroupId", nullable = false)
  private ControlGroup controlGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("accessControlId") // Maps the embedded ID to AccessControl
  @JoinColumn(name = "access_control_id", referencedColumnName = "access_control_id", nullable = false)
  private AccessControl accessControl;

}
