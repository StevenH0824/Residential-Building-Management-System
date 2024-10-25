package com.example.buildingmanagement.dtos;

import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class AccessLogDto {

  private Long accessLogId;
  private CardScanner cardScanner;
  private Person person;
  private LocalDateTime access_time;

}
