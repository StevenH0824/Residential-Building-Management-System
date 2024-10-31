package com.example.buildingmanagement.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ControlGroupPersonId implements Serializable {
  private Long controlGroupId; // This should match ControlGroup's ID field
  private Long personId;        // This should match Person's ID field

  // Default constructor
  public ControlGroupPersonId() {}

  public ControlGroupPersonId(Long controlGroupId, Long personId) {
    this.controlGroupId = controlGroupId;
    this.personId = personId;
  }

  // Getters and setters
  public Long getControlGroupId() {
    return controlGroupId;
  }

  public void setControlGroupId(Long controlGroupId) {
    this.controlGroupId = controlGroupId;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ControlGroupPersonId)) return false;
    ControlGroupPersonId that = (ControlGroupPersonId) o;
    return Objects.equals(controlGroupId, that.controlGroupId) &&
      Objects.equals(personId, that.personId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(controlGroupId, personId);
  }
}
