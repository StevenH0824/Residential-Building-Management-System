package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.ControlGroupAccessControlRequestDTO;
import com.example.buildingmanagement.dtos.ControlGroupAccessControlResponseDTO;
import com.example.buildingmanagement.entities.ControlGroupAccessControl;
import com.example.buildingmanagement.entities.ControlGroupAccessControlId;
import com.example.buildingmanagement.repository.AccessControlRepository;
import com.example.buildingmanagement.repository.ControlGroupAccessControlRepository;
import com.example.buildingmanagement.repository.ControlGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControlGroupAccessControlService {

  private final ControlGroupAccessControlRepository repository;
  private final ControlGroupRepository controlGroupRepository;
  private final AccessControlRepository accessControlRepository;

  @Autowired
  public ControlGroupAccessControlService(ControlGroupAccessControlRepository repository,
                                          ControlGroupRepository controlGroupRepository,
                                          AccessControlRepository accessControlRepository) {
    this.repository = repository;
    this.controlGroupRepository = controlGroupRepository;
    this.accessControlRepository = accessControlRepository;
  }

  public List<ControlGroupAccessControlResponseDTO> findAll() {
    return repository.findAll().stream()
      .map(this::convertToResponseDTO)
      .collect(Collectors.toList());
  }


  public Optional<ControlGroupAccessControlResponseDTO> findById(ControlGroupAccessControlId id) {
    return repository.findById(id).map(this::convertToResponseDTO);
  }

  public ControlGroupAccessControlResponseDTO save(ControlGroupAccessControlRequestDTO requestDTO) {
    ControlGroupAccessControl accessControl = new ControlGroupAccessControl();
    accessControl.setId(new ControlGroupAccessControlId(requestDTO.getControlGroupId(), requestDTO.getAccessControlId()));
    accessControl.setControlGroup(controlGroupRepository.findById(requestDTO.getControlGroupId()).orElse(null));
    accessControl.setAccessControl(accessControlRepository.findById(requestDTO.getAccessControlId()).orElse(null));
    return convertToResponseDTO(repository.save(accessControl));
  }


  public void deleteById(ControlGroupAccessControlId id) {
    repository.deleteById(id);
  }

  private ControlGroupAccessControlResponseDTO convertToResponseDTO(ControlGroupAccessControl accessControl) {
    return new ControlGroupAccessControlResponseDTO(
      accessControl.getId().getControlGroupId(),
      accessControl.getId().getAccessControlId()
      // Add other fields as necessary
    );
  }
}
