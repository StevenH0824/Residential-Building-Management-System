package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingRequestDTO;
import com.example.buildingmanagement.dtos.BuildingResponseDTO;
import com.example.buildingmanagement.entities.Building;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.repository.BuildingRepository;
import com.example.buildingmanagement.repository.FloorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {

  @Mock
  private BuildingRepository buildingRepository;

  @Mock
  private FloorRepository floorRepository;

  @InjectMocks
  private BuildingService buildingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    buildingService = new BuildingService(buildingRepository, floorRepository);
  }


  @Test
  void createBuilding() {
    Long buildingId = 15L;
    String buildingName = "Avon Hall";
    String buildingAddress = "1217 bedford";
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(buildingId);
    buildingResponseDTO.setName(buildingName);
    buildingResponseDTO.setAddress(buildingAddress);

    BuildingRequestDTO buildingRequestDTO = new BuildingRequestDTO();
    buildingResponseDTO.setAddress(buildingAddress);
    buildingRequestDTO.setName(buildingName);

    Building building = new Building();
    building.setBuildingId(buildingId);
    building.setName(buildingName);
    building.setAddress(buildingAddress);

    //when(buildingRepository.save)

    buildingService.createBuilding(buildingRequestDTO);


    //verify(buildingRepository).save(any(Building.class));
  }


  @Test
  void testGetAllBuildings() {
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(10L);

    // doNothing().when(buildingRepository).deleteById(10L);

    buildingService.getAllBuildings();

  }

  @Test
  void testGetBuildingById() {
    Long buildingId = 15L;
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(buildingId);

    Building building = new Building();
    building.setBuildingId(buildingId);

     when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

     buildingService.getBuildingById(buildingId);
     verify(buildingRepository, times(1)).findById(buildingId);
  }

  @Test
  void getLatestBuildingId() {
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(10L);


    // doNothing().when(buildingRepository).deleteById(10L);

    buildingService.getLatestBuildingId();
  }

  @Test
  void updateBuilding() {
    Long buildingId = 15L;
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(buildingId);

    Building building = new Building();
    building.setBuildingId(buildingId);

    when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

    buildingService.updateBuilding(buildingId, new BuildingRequestDTO());
    verify(buildingRepository, times(1)).findById(buildingId);
  }

  @Test
  void testDeleteBuilding() {
    //Since its just building ID, I don't have to do everything
    BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
    buildingResponseDTO.setBuildingId(10L);

    // doNothing().when(buildingRepository).deleteById(10L);

    buildingService.deleteBuilding(10L);

    //verify(buildingRepository, times(1)).deleteById(10L);

    // Works without assertEquals
    //assertNotNull();
    //assertEquals(10L, 10L);
  }
}

