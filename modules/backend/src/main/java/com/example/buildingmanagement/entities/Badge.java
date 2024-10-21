//package com.example.buildingmanagement.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//@Entity
//@Table(name = "badge")
//public class Badge {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "badge_id")
//  private Long badgeId;
//
//  @ManyToOne
//  @JoinColumn(name = "person_id", nullable = false)
//  private Person personId;
//
//  @Column(name = "issued_date", nullable = false)
//  private LocalDate issuedDate;
//}
