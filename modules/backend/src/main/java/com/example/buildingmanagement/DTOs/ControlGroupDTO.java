package com.example.buildingmanagement.DTOs;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Person;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ControlGroupDTO {
  private Long id;
  private LocalDateTime date;
  private Person person;
  private Floor floor;
}
