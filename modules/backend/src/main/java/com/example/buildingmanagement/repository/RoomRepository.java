package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  Room findByRoomId(Long id);
  List<Room> findByNumber(String Number);
  List<Room> findByDescription(String Description);
  Room findByFloor(Floor id);


}

