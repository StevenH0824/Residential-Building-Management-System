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
  private String first_name;
  private String last_name;
  private String phone;
  private String email;
  @ManyToOne
  @JoinColumn(name = "control_group_id")
  private ControlGroup controlGroupId;
}

