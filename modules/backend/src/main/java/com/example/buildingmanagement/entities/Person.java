package com.example.buildingmanagement.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "person_id")
  private Long personId;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  // One-to-Many relationships with AccessControlPerson, AccessRequest, and Badge
  //  @OneToMany(mappedBy = "personId")
  //  private List<AccessControlPerson> accessControlPersons;

//  @OneToMany(mappedBy = "access_log_id")
//  private List<AccessLog> accessLogs;

//  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  private List<ControlGroupAccessControl> controlGroupAccessControls;
}
