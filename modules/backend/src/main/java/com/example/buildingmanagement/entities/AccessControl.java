package com.example.buildingmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashMap;
// git check comment
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccessControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long cardScannerId;
  private HashMap<Long, Boolean> floorAccess;

}
