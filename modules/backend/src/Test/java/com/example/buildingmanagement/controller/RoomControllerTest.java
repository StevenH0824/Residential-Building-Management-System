package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RoomService roomService;


  @BeforeEach
  void setUp() {
  }

  @Test
  void getRoomById_success() throws Exception {
    RoomResponseDTO roomDTO = new RoomResponseDTO();
    roomDTO.setRoomId(123L);
    try {
      when(roomService.getRoomById(123L)).thenReturn(roomDTO);

      mockMvc.perform((RequestBuilder) get("/api/rooms/123"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.roomid").value(123));


    }  catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void getRoomByNumber() {
    List<RoomResponseDTO> roomDTOs = new ArrayList<>();
    RoomResponseDTO roomDTO1 = new RoomResponseDTO();
    roomDTO1.setRoomId(123L);
    roomDTO1.setNumber("1");
    roomDTOs.add(roomDTO1);
    try {
      when(roomService.getRoomByNumber("1")).thenReturn(roomDTOs);

      mockMvc.perform((RequestBuilder) get("/api/rooms/1"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.roomnumber").value("1"));


    }  catch (Exception e) {
      e.printStackTrace();
    }
  }




  @Test
  void getRoomByDescription() {

    List<RoomResponseDTO> roomDTOs = new ArrayList<>();
    RoomResponseDTO roomDTO1 = new RoomResponseDTO();
    roomDTO1.setRoomId(123L);
    roomDTO1.setNumber("1");
    roomDTO1.setDescription("test room");
    roomDTOs.add(roomDTO1);
    try {
      when(roomService.getRoomByDescription("test room")).thenReturn(roomDTOs);

      mockMvc.perform((RequestBuilder) get("/api/rooms/description"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.description").value("test room"));


    }  catch (Exception e) {
      e.printStackTrace();
    }




  }

  @Test
  void getRoomByFloorId() {
    List<RoomResponseDTO> roomDTOs = new ArrayList<>();
    RoomResponseDTO roomDTO1 = new RoomResponseDTO();
    roomDTO1.setRoomId(123L);
    roomDTO1.setNumber("1");
    roomDTO1.setDescription("test room");
    roomDTO1.setFloorId(1L);
    roomDTOs.add(roomDTO1);
    try {
      when(roomService.getRoomByFloorId(1L)).thenReturn(roomDTOs);

      mockMvc.perform((RequestBuilder) get("/api/rooms/floorid"))
        .andExpect(status().isOk())
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
        .andExpect((ResultMatcher) jsonPath("$.floorid").value(1L));


    }  catch (Exception e) {
      e.printStackTrace();
    }





  }
}
