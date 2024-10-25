package com.example.buildingmanagement.entities;

import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class BuildingTest {

  Building building;
  List<Floor> floors;
  List<Room> rooms;

  @BeforeEach
  void init() {
    floors = new ArrayList<>();
    rooms = new ArrayList<>();

    building = new Building(1L,"Hidden Leaf","123 Main Street",floors); // null needs to be a list of floors

    Floor floor1 = new Floor(1L,"1","First Floor",building,rooms);
    Room room1 = new Room(1L,"101","Room 101",floor1);
    Room room2 = new Room(1L,"102","Room 102",floor1);

    rooms.add(room1);
    rooms.add(room2);
    floor1.setRooms(rooms);


    Floor floor2 = new Floor(1L,"2","Second Floor",building,rooms);
    Room room3 = new Room(1L,"201","Room 201",floor1);
    Room room4 = new Room(1L,"202","Room 202",floor1);
    rooms = new ArrayList<>();
    rooms.add(room3);
    rooms.add(room4);
    floor2.setRooms(rooms);

    floors.add(floor1);
    floors.add(floor2);
    building.setFloors(floors);
  }

  @Test
  void getBuildingId() {
    assertEquals(building.getBuildingId(),1L,"Building id is wrong");
  }

//  @Test
//  void getBuildingName(){
////    assertEquals();
//  }



}
