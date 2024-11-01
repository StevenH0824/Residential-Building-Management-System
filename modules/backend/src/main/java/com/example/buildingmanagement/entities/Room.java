package com.example.buildingmanagement.entities;

import aj.org.objectweb.asm.ConstantDynamic;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name="number")
    private String number;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    public String getRoomNumber() {
      return number;
    }

  // a method to get the associated building through the floor
  public Building getBuilding() {
    return floor != null ? floor.getBuilding() : null;
  }
}
