package com.example.buildingmanagement.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ControlGroupAccessControlId implements Serializable {
  private Long controlGroupId; // Reference to ControlGroup
  private Long accessControlId; // Reference to AccessControl

  // Default constructor
  public ControlGroupAccessControlId() {}

  public ControlGroupAccessControlId(Long controlGroupId, Long accessControlId) {
    this.controlGroupId = controlGroupId;
    this.accessControlId = accessControlId;
  }

  // Getters and Setters
  public Long getControlGroupId() {
    return controlGroupId;
  }

  public void setControlGroupId(Long controlGroupId) {
    this.controlGroupId = controlGroupId;
  }

  public Long getAccessControlId() {
    return accessControlId;
  }

  public void setAccessControlId(Long accessControlId) {
    this.accessControlId = accessControlId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ControlGroupAccessControlId)) return false;
    ControlGroupAccessControlId that = (ControlGroupAccessControlId) o;
    return Objects.equals(controlGroupId, that.controlGroupId) &&
      Objects.equals(accessControlId, that.accessControlId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(controlGroupId, accessControlId);
  }
}
