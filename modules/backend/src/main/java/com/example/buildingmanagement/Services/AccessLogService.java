package com.example.buildingmanagement.Services;

import com.example.buildingmanagement.DTOs.AccessLogRequestDTO;
import com.example.buildingmanagement.DTOs.AccessLogResponseDTO;
import com.example.buildingmanagement.Mapper.AccessLogMapper;
import com.example.buildingmanagement.Repositories.AccessLogRepository;
import com.example.buildingmanagement.entities.AccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogService {
  //This class handles business logic

  @Autowired
  private AccessLogRepository accessLogRepository;

  @Autowired
  private AccessLogMapper accessLogMapper;

  public AccessLogResponseDTO createAccessLog(AccessLogRequestDTO accessLogRequestDTO){
    //To convert DTO to entity
    AccessLog accessLog = accessLogMapper.toEntity(accessLogRequestDTO);

    //To save all AccessLog
    AccessLog savedAccessLog = accessLogRepository.save(accessLog);

    //To convert from entity to DTO
    AccessLogResponseDTO accessLogResponseDTO = accessLogMapper.toDTO(savedAccessLog);

    return accessLogResponseDTO;

  }

}
