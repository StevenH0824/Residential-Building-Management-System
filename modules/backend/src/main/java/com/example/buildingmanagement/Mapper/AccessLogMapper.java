package com.example.buildingmanagement.Mapper;

import com.example.buildingmanagement.DTOs.AccessLogRequestDTO;
import com.example.buildingmanagement.DTOs.AccessLogResponseDTO;
import com.example.buildingmanagement.entities.AccessLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessLogMapper {
  //convert accessLogRequestDto to Entity
  AccessLog toEntity(AccessLogRequestDTO accessLogRequestDTO);


  //convert Entity accessLogResponseDto
  AccessLogResponseDTO toDTO(AccessLog accessLog);
}
