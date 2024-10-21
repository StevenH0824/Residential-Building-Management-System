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

  //  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne
  @JoinColumn(name = "building_id", nullable = true)
  private Building building;

//  @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @OneToMany(mappedBy = "floor")
  private List<Apartment> apartments;

//  @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @OneToMany(mappedBy = "floor")
  private List<SpecialRoom> specialRooms;
}
  /*
  Just focus on getting the building and floors to show up. lol, I believed I lied to myself!

  //  @OneToMany(mappedBy = "floor")
  //  private List<Room> rooms;
   */







