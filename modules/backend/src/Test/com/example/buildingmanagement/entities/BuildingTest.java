package com.example.buildingmanagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

  private Building building;

  @BeforeEach
  void setUp() {
    building = new Building();
  }

  @Test
  void getBuildingId() {
    building.setBuildingId(1L);
    assertEquals(1L, building.getBuildingId());
  }

  @Test
  void getName() {
    building.setName("Main Building");
    assertEquals("Main Building", building.getName());
  }

  @Test
  void getAddress() {
    building.setAddress("123 Main St");
    assertEquals("123 Main St", building.getAddress());
  }

  @Test
  void getFloors() {
    List<Floor> floors = new ArrayList<>();
    floors.add(new Floor()); // Assuming you have a Floor class
    building.setFloors(floors);
    assertEquals(floors, building.getFloors());
  }

  @Test
  void setBuildingId() {
    building.setBuildingId(2L);
    assertEquals(2L, building.getBuildingId());
  }

  @Test
  void setName() {
    building.setName("East Wing");
    assertEquals("East Wing", building.getName());
  }

  @Test
  void setAddress() {
    building.setAddress("456 East Rd");
    assertEquals("456 East Rd", building.getAddress());
  }

  @Test
  void setFloors() {
    List<Floor> floors = new ArrayList<>();
    floors.add(new Floor()); // Assuming you have a Floor class
    building.setFloors(floors);
    assertEquals(floors, building.getFloors());
  }

  @Test
  void testToString() {
    building.setBuildingId(1L);
    building.setName("Main Building");
    building.setAddress("123 Main St");

    String expectedString = "Building(buildingId=1, name=Main Building, address=123 Main St)";
    assertEquals(expectedString, building.toString());
  }
}
