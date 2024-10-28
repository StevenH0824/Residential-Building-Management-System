package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.BuildingDTO;
import com.example.buildingmanagement.dtos.BuildingRequestDTO;
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
  }

  @Test
  void getAllBuildings() {
  }

  @Test
  void getBuildingById() {
    BuildingRequestDTO buildingRequestDTO = new BuildingRequestDTO();
    buildingRequestDTO.setBuildingId(15L);

   // when(buildingRepository.findById(15L)).thenReturn(new Building());

    buildingService.getBuildingById(15L);
    //verify(buildingRepository, times(1)).findById(15L);
  }

  @Test
  void saveBuilding() {
    Floor floor = new Floor();
    floor.setFloorId(1L);


    BuildingRequestDTO buildingRequestDTO = new BuildingRequestDTO();
    buildingRequestDTO.setBuildingId(10L);
    buildingRequestDTO.setName("Avon hall");
    buildingRequestDTO.setAddress("1217 bedford");


//    Building building = new Building();
//    building.setBuildingId(10L);
//    building.setName("Avon hall");
//    building.setAddress("1217 bedford");
//    building.setFloors(List.of());

    BuildingDTO building = buildingService.saveBuilding(buildingRequestDTO);

    //when(buildingRepository.save(any())).thenReturn(new BuildingRequestDTO[]);

    //verify(buildingRepository).save(any( Building.class));
  }

  @Test
  void deleteBuilding() {
    //Since its just building ID, I don't have to do everything
    BuildingRequestDTO buildingRequestDTO = new BuildingRequestDTO();
    buildingRequestDTO.setBuildingId(10L);

    // doNothing().when(buildingRepository).deleteById(10L);

    buildingService.deleteBuilding(10L);

    //verify(buildingRepository, times(1)).deleteById(10L);

    // Works without assertEquals
    //assertNotNull();
    //assertEquals(10L, 10L);


  }
}
