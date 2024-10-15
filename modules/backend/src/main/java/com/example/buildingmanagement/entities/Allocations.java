package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Allocations {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  private List<Long> personId;
@ManyToMany
@JoinTable(
  name = "allocation_person",
  joinColumns = @JoinColumn(name = "allocation_id"),
  inverseJoinColumns = @JoinColumn(name = "person_id")
)
private Set<Person> persons;
  @ManyToOne
  @JoinColumn(name = "apartment_id", nullable = false)
  private Apartment apartment;
}
