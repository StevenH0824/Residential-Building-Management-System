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
  private Long id;
  private String full_name;
  @ManyToOne
  @JoinColumn(name = "control_group_id")
  private Long controlGroupId;

}
