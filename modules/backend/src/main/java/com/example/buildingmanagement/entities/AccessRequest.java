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
public class AccessRequest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long accessRequestId;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne
  @JoinColumn(name = "control_group_id", nullable = false)
  private ControlGroup controlGroup;

  @Enumerated(EnumType.STRING)
  private StatusType status;
  private LocalDateTime requestedDate;
  private LocalDateTime decisionDate;
  private String reason;

//  @ManyToOne
//  @JoinColumn(name = "processed_by")
//  private Person processedBy;
  /*
  commit b4f5f100db923d3a95bf887b69065c674db88031 (origin/danaBranch)
  Author: Dana <dana.seidakhmetova@peopleshores.com>
  Date:   Thu Oct 17 15:44:36 2024 -0400
  Changed the accessRequest filed from Person accessedBy to String
  This change causes an error in the database, Spring boot still runs but this creates
  ambiguity between processedBy which is the primary key and foreign key of another table.
  Check the ER diagram in dbeaver for more information.

  I added the Person processedBy id back as the latest git changes. We can try to see how to
  incorportate processedBy as a String id but for now, no errors are given at all.
   */

  private String processedBy;  // Admin or system processing the request
  @ManyToOne
  @JoinColumn(name = "floor_id")
  private Floor floor;

}
