package com.example.buildingmanagement.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessControlDTO {
  private Long id;
  private Long cardScanId;
  private Map<Long, Boolean> floorAccess;
}
