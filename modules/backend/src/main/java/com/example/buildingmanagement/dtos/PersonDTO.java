package com.example.buildingmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
  private Long personId;              // ID of the person
  private String email;               // Email of the person
  private String firstName;           // First name of the person
  private String lastName;            // Last name of the person
  private String phoneNumber;          // Phone number of the person

 // private List<Long> accessControlPersonIds; // List of associated access control person IDs
 // private List<Long> badgeIds;        // List of associated badge IDs
 // private List<Long> controlGroupAccessControlIds; // List of associated control group access control IDs
}
