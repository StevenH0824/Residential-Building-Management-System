package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
<<<<<<<< HEAD:modules/backend/src/main/java/com/example/buildingmanagement/dtos/AccessLogResponseDTO.java
public class AccessLogResponseDTO {
========
public class ControlGroupAccessControlDTO {
>>>>>>>> origin/dev:modules/backend/src/main/java/com/example/buildingmanagement/dtos/ControlGroupAccessControlDTO.java
  private Long id;
  private Long personId;
<<<<<<<< HEAD:modules/backend/src/main/java/com/example/buildingmanagement/dtos/AccessLogResponseDTO.java
  private LocalDateTime accessTime;
  //Request DTOs typically include only
  // the data needed from the client to perform an operation
  // (e.g., for creating or updating a resource). Output
========
  private Long controlGroupId;
  private Long accessControlId;
>>>>>>>> origin/dev:modules/backend/src/main/java/com/example/buildingmanagement/dtos/ControlGroupAccessControlDTO.java
}
