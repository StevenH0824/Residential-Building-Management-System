package com.example.buildingmanagement.DTOs;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;
}
