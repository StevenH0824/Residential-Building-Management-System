package com.example.buildingmanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

  @BeforeEach
  void setUp() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
  }

  @Test
  void createRoom(){
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    assertNotNull(room);


  }

  @Test
  void getRoomId() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    assertEquals(room.getRoomId(),1234L);
  }

  @Test
  void getNumber() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    assertEquals(room.getNumber(), "1");

  }

  @Test
  void getDescription() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    assertEquals(room.getDescription(), "test");
  }

  @Test
  void getFloor() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    assertEquals(room.getFloor(), floor);

  }

  @Test
  void setRoomId() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    room.setRoomId(5678L);
    assertEquals(room.getRoomId(),5678L);

  }

  @Test
  void setNumber() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    room.setNumber("2");
    assertEquals(room.getNumber(),"2");

  }

  @Test
  void setDescription() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    room.setDescription("newtest");
    assertEquals(room.getDescription(), "newtest");


  }

  @Test
  void setFloor() {
    Building building = new Building();
    List<Room> roomList = new ArrayList<>();
    Floor floor = new Floor(1234L, "1", "test", building,roomList);
    Floor floor2 = new Floor(5678L,"2","newtest",building,roomList);
    Room room = new Room(1234L, "1", "test", floor);
    room.setFloor(floor2);
    assertEquals(room.getFloor(),floor2);

  }

}
