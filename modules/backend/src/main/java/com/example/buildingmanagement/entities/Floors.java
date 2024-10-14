package com.example.buildingmanagement.entities;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Floors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
