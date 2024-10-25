package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

//will be needed when we start connecting the room to apartments and floors.
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "floor")
public class Floor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long floorId;
  @Column(name = "number")
  private String number;
  @Column(name = "description")
  private String description;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "building_id", nullable = true)
  private Building building;
  @OneToMany(mappedBy = "floor")
  private List<Room> rooms;

}







