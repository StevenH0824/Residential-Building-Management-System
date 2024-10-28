package com.example.buildingmanagement.service;

import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Room;
import com.example.buildingmanagement.repository.FloorRepository;
import com.example.buildingmanagement.repository.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.any;

class RoomServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private RoomService roomService;

  @Autowired
  private ObjectMapper objectMapper;

  @Mock
  private RoomRepository roomRepository;

  @Mock
  private FloorRepository floorRepository;




  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);


  }

  @Test
  void getRoomById() {
    Floor floor = new Floor();
    floor.setFloorId(1L);
    floor.setDescription("Test Floor");
    Room room = new Room(1234L, "1", "Test Room", floor);
    when(roomRepository.findByRoomId(1234L)).thenReturn(room);
    RoomResponseDTO result = roomService.getRoomById(1234L);
    assertEquals(1234L, result.getRoomId());
    assertEquals("Test Room", result.getDescription());
    assertEquals("1", result.getNumber());
    assertEquals(1L, result.getFloorId());
    assertEquals("Test Floor", result.getFloorDescription());

  }

  @Test
  void getRoomByNumber() {
    Floor floor = new Floor();
    floor.setFloorId(1L);
    floor.setDescription("test");
    Room room = new Room(1234L, "1", "Test Room", floor);
    Floor floor2 = new Floor();
    floor2.setFloorId(2L);
    floor2.setDescription("test2");
    Room room2 = new Room(5678L, "1", "Test Room2", floor2);
    when(roomRepository.findByNumber("1")).thenReturn(Arrays.asList(room, room2));
    List<RoomResponseDTO> result = roomService.getRoomByNumber("1");
    assertEquals(2, result.size());
    assertEquals(1234L, room.getRoomId());
    assertEquals(5678L, room2.getRoomId());
    assertEquals("Test Room", result.get(0).getDescription());
    assertEquals("1",result.get(1).getNumber());





  }

  @Test
  void getRoomByDescription() {
    Floor floor = new Floor();
    floor.setFloorId(1L);
    floor.setDescription("test");
    Room room = new Room(1234L, "1", "Test Room", floor);
    Floor floor2 = new Floor();
    floor2.setFloorId(2L);
    floor2.setDescription("test2");
    Room room2 = new Room(5678L, "1", "Test Room2", floor2);
    when(roomRepository.findByDescription("Test Room")).thenReturn(Arrays.asList(room, room2));
    List<RoomResponseDTO> result = roomService.getRoomByDescription("Test Room");
    assertEquals(2, result.size());
    assertEquals("Test Room", result.get(0).getDescription());
    assertEquals("Test Room2", result.get(1).getDescription());


  }

  @Test
  void getRoomByFloorId() {
    Floor floor = new Floor();
    floor.setFloorId(1L);
    floor.setDescription("test");
    Room room = new Room(1234L, "1", "Test Room", floor);
    Room room2 = new Room(5678L, "1", "Test Room2", floor);
    when(roomRepository.findByFloor(floor)).thenReturn(Arrays.asList(room, room2));
    when(floorRepository.findByFloorId(1L)).thenReturn(floor);
    List<RoomResponseDTO> result = roomService.getRoomByFloorId(1L);
    assertEquals(2, result.size());
    assertEquals("Test Room", result.get(0).getDescription());
    assertEquals("Test Room2", result.get(1).getDescription());

  }
}
