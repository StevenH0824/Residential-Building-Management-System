package com.example.buildingmanagement.DTOs;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonDTO {
  private Long id;
  private String first_name;
  private String last_name;
  private String phone_number;
  private String email;
}
