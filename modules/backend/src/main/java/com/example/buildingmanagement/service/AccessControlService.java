package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.AccessControlRequestDTO;
import com.example.buildingmanagement.dtos.AccessControlResponseDTO;
import com.example.buildingmanagement.entities.AccessControl;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.AccessControlRepository;
import com.example.buildingmanagement.repository.CardScannerRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccessControlService {
  private final AccessControlRepository accessControlRepository;
  private final CardScannerRepository cardScannerRepository;
  private final RoomRepository roomRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public AccessControlService(AccessControlRepository accessControlRepository, CardScannerRepository cardScannerRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
    this.accessControlRepository = accessControlRepository;
    this.cardScannerRepository = cardScannerRepository;
    this.roomRepository = roomRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public List<AccessControl> getAllAccessControls() {
    return accessControlRepository.findAll();
  }

  public Optional<AccessControl> getAccessControlById(Long id) {
    return accessControlRepository.findById(id);
  }

  public AccessControl createAccessControl(AccessControlRequestDTO accessControl) {
// Use ModelMapper to convert DTO to AccessControl entity
    AccessControl newAccessControl = modelMapper.map(accessControl, AccessControl.class);

    // Fetch related entities: CardScanner and Room
    CardScanner cardScanner = cardScannerRepository.findById(accessControl.getCardScannerId())
      .orElseThrow(() -> new RuntimeException("CardScanner not found"));

    newAccessControl.setCardScanner(cardScanner);

    Room room = roomRepository.findById(accessControl.getRoomId())
      .orElseThrow(() -> new RuntimeException("Room not found"));

    newAccessControl.setRoom(room);

    // Save and return the AccessControl entity
    return accessControlRepository.save(newAccessControl);
  }


  public void deleteAccessControl(Long id) {
    accessControlRepository.deleteById(id);
  }

  public List<AccessControl> findByControlGroupId(Long controlGroupId) {
    return accessControlRepository.findByControlGroupAccessControls_ControlGroup_ControlGroupId(controlGroupId);
  }

  public List<AccessControlResponseDTO> findByDescription(String description) {
    return accessControlRepository.findByDescription(description);
  }

  public List<AccessControlResponseDTO> findByCardScanner(Long cardScannerId) {
    return accessControlRepository.findByCardScanner_CardScannerId(cardScannerId);
  }

  public List<AccessControl> findByRoomId(Long roomId) {
    return accessControlRepository.findByRoom_RoomId(roomId);
  }

}
