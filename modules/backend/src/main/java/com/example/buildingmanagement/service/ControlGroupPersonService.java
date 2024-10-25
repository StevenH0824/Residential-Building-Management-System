package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.ControlGroupPersonRequestDTO;
import com.example.buildingmanagement.entities.ControlGroupPerson;
import com.example.buildingmanagement.repository.ControlGroupPersonRepository;
import com.example.buildingmanagement.repository.ControlGroupRepository;
import com.example.buildingmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlGroupPersonService {

  @Autowired
  private ControlGroupPersonRepository controlGroupPersonRepository;

  @Autowired
  private ControlGroupRepository controlGroupRepository;

  @Autowired
  private PersonRepository personRepository;

  public ControlGroupPerson createControlGroupPerson (ControlGroupPersonRequestDTO controlGroupPersonRequestDTO) {
    ControlGroupPerson controlGroupPerson = new ControlGroupPerson();
    controlGroupPerson.setControlGroup(controlGroupRepository.getReferenceById(controlGroupPersonRequestDTO.getControlGroupId()));
    controlGroupPerson.setPerson(personRepository.getReferenceById(controlGroupPersonRequestDTO.getPersonId()));
    controlGroupPerson.setStart_date(controlGroupPersonRequestDTO.getStartDate());
    controlGroupPerson.setExpiration_date(controlGroupPersonRequestDTO.getExpirationDate());

    return controlGroupPersonRepository.save(controlGroupPerson);
  }
}
