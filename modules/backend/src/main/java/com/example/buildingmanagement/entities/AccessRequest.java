//package com.example.buildingmanagement.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Getter
//@Setter
//public class AccessRequest {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long accessRequestId;
//
//  @ManyToOne
//  @JoinColumn(name = "person_id", nullable = false)
//  private Person person;
//
//  @ManyToOne
//  @JoinColumn(name = "access_control_id", nullable = false)
//  private AccessControl access_control_id; // Group the person is requesting access to
//
//  @Enumerated(EnumType.STRING) // Assuming StatusType is an enum
//  private StatusType status;
//
//  private LocalDateTime requestedDate;
//  private LocalDateTime decisionDate;
//  private String reason;
//
//  @ManyToOne
//  @JoinColumn(name = "processed_by")
//  private Person processedBy; // Admin or system processing the request
//}
//
//

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
  @JoinColumn(name = "access_group_id", nullable = false) // Updated to point to AccessGroup
  private AccessGroup accessGroup; // Group the person is requesting access to

  @Enumerated(EnumType.STRING) // Assuming StatusType is an enum
  private StatusType status;

  private LocalDateTime requestedDate;
  private LocalDateTime decisionDate;
  private String reason;

  @ManyToOne
  @JoinColumn(name = "processed_by")
  private Person processedBy; // Admin or system processing the request
}

