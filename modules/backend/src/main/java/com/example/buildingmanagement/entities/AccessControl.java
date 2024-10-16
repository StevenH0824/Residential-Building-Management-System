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
public class AccessControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accessControlId;
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
  @ManyToOne
  @JoinColumn(name = "control_group_id", nullable = false)
  private ControlGroup controlGroupId;
  @ManyToOne
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
  private LocalDateTime startDate;
  private LocalDateTime endDate;




}
