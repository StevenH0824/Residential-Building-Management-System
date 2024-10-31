package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.ControlGroupRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupResponseDTO;
import com.example.buildingmanagement.entities.ControlGroup;
import com.example.buildingmanagement.repository.ControlGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControlGroupService {

  private final ControlGroupRepository controlGroupRepository;

  @Autowired
  public ControlGroupService(ControlGroupRepository controlGroupRepository) {
    this.controlGroupRepository = controlGroupRepository;
  }

  public List<ControlGroupResponseDTO> findAll() {
    return controlGroupRepository.findAll().stream()
      .map(this::convertToResponseDTO)
      .collect(Collectors.toList());
  }

  public Optional<ControlGroupResponseDTO> findById(Long id) {
    return controlGroupRepository.findById(id)
      .map(this::convertToResponseDTO);
  }

  public ControlGroupResponseDTO save(ControlGroupRequestDTO controlGroupRequest) {
    ControlGroup controlGroup = new ControlGroup();
    controlGroup.setName(controlGroupRequest.getName());
    controlGroup.setDescription(controlGroupRequest.getDescription());
    return convertToResponseDTO(controlGroupRepository.save(controlGroup));
  }

  public void deleteById(Long id) {
    controlGroupRepository.deleteById(id);
  }

  private ControlGroupResponseDTO convertToResponseDTO(ControlGroup controlGroup) {
    return new ControlGroupResponseDTO(
      controlGroup.getControlGroupId(),
      controlGroup.getName(),
      controlGroup.getDescription(),
      controlGroup.getControlGroupAccessControls().stream()
        .map(accessControl -> accessControl.getId().getAccessControlId())
        .collect(Collectors.toList()),
      controlGroup.getControlGroupPersons().stream()
        .map(person -> person.getId().getPersonId())
        .collect(Collectors.toList())
    );
  }
}
