package com.example.buildingmanagement.service;

import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.entities.ControlGroupAccessControl; // Ensure this is imported
import com.example.buildingmanagement.entities.ControlGroupPerson;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.ControlGroupPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ControlGroupPersonService {

  @Autowired
  private ControlGroupPersonRepository controlGroupPersonRepository;

  public List<Room> getAccessibleRoomsByPersonId(Long personId) {
    // Fetch ControlGroupPersons by personId
    List<ControlGroupPerson> controlGroupPersons = controlGroupPersonRepository.findByPerson_PersonId(personId);

    if (controlGroupPersons.isEmpty()) {
      throw new EntityNotFoundException("No control group associations found for person ID: " + personId);
    }

    // Extract rooms from the ControlGroupPersons
    return controlGroupPersons.stream()
      .flatMap(cgp -> {
        ControlGroup controlGroup = cgp.getControlGroup();
        return (controlGroup != null && controlGroup.getControlGroupAccessControls() != null)
          ? controlGroup.getControlGroupAccessControls().stream()
          .map(ControlGroupAccessControl::getAccessControl)
          .map(AccessControl::getRoom)
          : Stream.empty();
      })
      .filter(Objects::nonNull)
      .distinct()
      .collect(Collectors.toList());
  }
}
