package com.example.buildingmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name="number")
    private String number;
    @Column(name="description")
    private String description;

    // @ManyToOne(fetch = FetchType.LAZY) // is this needed at all? Will have to read docs later.
    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

}
