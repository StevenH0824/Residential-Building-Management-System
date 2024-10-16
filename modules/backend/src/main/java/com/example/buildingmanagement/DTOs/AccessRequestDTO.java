package com.example.buildingmanagement.DTOs;

import com.example.buildingmanagement.entities.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessRequestDTO {
    private Long id;
    private Long personId;
    private Long controlGroupId;
    private StatusType status;
    private LocalDateTime RequestDate;
    private LocalDateTime DecisionDate;
    private String reason;
    private String proccessed_by;
    private Long floorId;


}

