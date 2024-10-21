package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BadgeDTO {
  private Long badgeId;         // ID of the badge
  private Long personId;        // ID of the associated person
  private LocalDate issuedDate; // Date the badge was issued
}
