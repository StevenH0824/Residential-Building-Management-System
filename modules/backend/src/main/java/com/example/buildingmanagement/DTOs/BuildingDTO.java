package com.example.buildingmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuildingDTO {
    private Long id;
    private String name;
    private String address;
    private Long totalFloors;

}
