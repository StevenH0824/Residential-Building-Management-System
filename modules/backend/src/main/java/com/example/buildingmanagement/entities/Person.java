package com.example.buildingmanagement.entities;


import jakarta.persistence.*;
import lombok.*;

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
  private Long personId;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;

  // New field for role
  private String role; // or use an enum for predefined roles
}
