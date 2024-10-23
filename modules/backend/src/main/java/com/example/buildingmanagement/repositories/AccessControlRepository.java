package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.dtos.AccessControlResponseDTO;
import com.example.buildingmanagement.entities.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
  // Find by description
  List<AccessControlResponseDTO> findByDescription(String description);

  // Find by CardScanner ID
  List<AccessControlResponseDTO> findByCardScanner_CardScannerId(Long cardScannerId);

  // Find by Room ID
  List<AccessControl> findByRoom_RoomId(Long roomId);

  // Find by ControlGroupId ID
  List<AccessControl> findByControlGroupAccessControls_ControlGroup_ControlGroupId(Long controlGroupId);
}


