package com.example.buildingmanagement.entities;

import java.io.Serializable;
import java.util.Objects;

public class ControlGroupAccessControlId implements Serializable {
  private Long controlGroup;
  private Long accessControl;

  // Default constructor
  public ControlGroupAccessControlId() {}

  // hashCode and equals methods
  @Override
  public int hashCode() {
    return Objects.hash(controlGroup, accessControl);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ControlGroupAccessControlId that = (ControlGroupAccessControlId) obj;
    return Objects.equals(controlGroup, that.controlGroup) &&
      Objects.equals(accessControl, that.accessControl);
  }
}
