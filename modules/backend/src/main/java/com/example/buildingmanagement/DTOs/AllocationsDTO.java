package com.example.buildingmanagement.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllocationsDTO {
  private Long id;
  private Set<Long> personIds;
  private Long apartmentId;
}
