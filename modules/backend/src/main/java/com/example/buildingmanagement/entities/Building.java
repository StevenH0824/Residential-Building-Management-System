package com.example.buildingmanagement.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Building {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long buildingId;
  private String name;
  private String address;
  @OneToMany(mappedBy = "building", cascade = CascadeType.ALL,orphanRemoval = true)
  @JsonIgnore // Prevents infinite recursion @JsonIgnore on the fields that create the circular reference.
  private List<Floor> floors;
}
