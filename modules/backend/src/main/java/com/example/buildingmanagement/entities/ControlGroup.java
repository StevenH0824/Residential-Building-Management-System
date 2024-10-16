package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ControlGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long controlGroupId;
  private String groupName;
  private String description;
}
