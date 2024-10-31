//package com.example.buildingmanagement.service;
//
//import com.example.buildingmanagement.dtos.ControlGroupAccessControlDTO;
//import com.example.buildingmanagement.dtos.ControlGroupRequestDTO;
//import com.example.buildingmanagement.dtos.ControlGroupResponseDTO;
//import com.example.buildingmanagement.entities.ControlGroup;
//import com.example.buildingmanagement.entities.ControlGroupAccessControl;
//import com.example.buildingmanagement.entities.ControlGroupAccessControlId;
//import com.example.buildingmanagement.repository.ControlGroupRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ControlGroupService {
//
//  @Autowired
//  private ControlGroupRepository controlGroupRepository;
//
//  public List<ControlGroupResponseDTO> findAll() {
//    return controlGroupRepository.findAll().stream()
//      .map(this::toResponseDTO)
//      .collect(Collectors.toList());
//  }
//
//  public Optional<ControlGroupResponseDTO> findById(Long id) {
//    return controlGroupRepository.findById(id)
//      .map(this::toResponseDTO);
//  }
//
//  public ControlGroupResponseDTO save(ControlGroupRequestDTO controlGroupRequestDTO) {
//    ControlGroup controlGroup = fromRequestDTO(controlGroupRequestDTO);
//    ControlGroup savedControlGroup = controlGroupRepository.save(controlGroup);
//    return toResponseDTO(savedControlGroup);
//  }
//
//  public void deleteById(Long id) {
//    controlGroupRepository.deleteById(id);
//  }
//
//  private ControlGroupResponseDTO toResponseDTO(ControlGroup controlGroup) {
//    return new ControlGroupResponseDTO(
//      controlGroup.getControlGroupId(),
//      controlGroup.getName(),
//      controlGroup.getDescription(),
//      convertAccessControlsToDTOs(controlGroup.getAccessControls()) // Ensure this returns List<ControlGroupAccessControlDTO>
//    );
//  }
//
//  // Ensure your convertAccessControlsToDTOs is implemented like this:
//  private List<ControlGroupAccessControlDTO> convertAccessControlsToDTOs(List<ControlGroupAccessControlDTO> accessControls) {
//    if (accessControls == null) {
//      return new ArrayList<>();
//    }
//
//    return accessControls.stream()
//      .map(accessControl -> new ControlGroupAccessControlDTO(
//        accessControl.getId().getControlGroupId(),  // Getting controlGroupId from the composite key
//        accessControl.getId().getAccessControlId()   // Getting accessControlId from the composite key
//      ))
//      .collect(Collectors.toList());
//  }
//
//  public ControlGroup fromRequestDTO(ControlGroupRequestDTO dto) {
//    return new ControlGroup(
//      null, // ControlGroupId should be null for creation
//      dto.getName(),
//      dto.getDescription(),
//      convertDTOsToAccessControls(dto.getAccessControls()) // Convert access controls
//    );
//  }
//
//  public List<ControlGroupAccessControl> convertDTOsToAccessControls(List<ControlGroupAccessControlDTO> dtos) {
//    if (dtos == null) {
//      return new ArrayList<>();
//    }
//
//    return dtos.stream()
//      .map(dto -> new ControlGroupAccessControl(
//        new ControlGroupAccessControlId(dto.getControlGroupId(), dto.getAccessControlId()), // Assuming you have a composite key
//        null, // ControlGroup will be set later
//        null  // AccessControl will be set later
//      ))
//      .collect(Collectors.toList());
//  }
//}
