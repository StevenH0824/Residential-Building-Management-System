package com.example.buildingmanagement.entities;

import jakarta.persistence.*;

@Entity
@Table(name="special_room")
public class SpecialRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long specialRoomId;

  @Column(name = "name")
  private String name;
  @Column(name = "purpose")
  private String purpose;
  @Column(name = "description")
  private String description;


  //  @ManyToOne(fetch = FetchType.LAZY) // is this needed, check docs later.
  @ManyToOne
  @JoinColumn(name = "floor_id", nullable = false)
  private Floor floor;
}
