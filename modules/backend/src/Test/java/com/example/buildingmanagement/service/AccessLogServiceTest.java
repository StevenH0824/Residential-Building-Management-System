package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.AccessLogDto;
import com.example.buildingmanagement.entities.AccessLog;
import com.example.buildingmanagement.entities.CardScanner;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.repository.AccessLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessLogServiceTest {

  @Mock
  AccessLogRepository accessLogRepository;

  @InjectMocks
  AccessLogService accessLogService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createAccessLog() {

    Person person = new Person(9L, "aKEN@gmail.com", "Alice", "Ken", "1234");
    CardScanner cardScanner = new CardScanner(9L,"12345", "Shiba", "New", List.of(), List.of());
    AccessLog result = accessLogService.createAccessLog(cardScanner, person);

    long seconds = Duration.between(result.getAccess_time(), LocalDateTime.now()).toSeconds();
    assertThat(seconds).isLessThan(1);

    when(accessLogRepository.save(any(AccessLog.class))).thenReturn(new AccessLog());

    assertNotNull(result);


  }

}

