package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.dtos.RoomResponseDTO;
import com.example.buildingmanagement.entities.Floor;
import com.example.buildingmanagement.entities.Person;
import com.example.buildingmanagement.entities.Room;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
  Room findByRoomId(Long id);

  List<Room> findByNumber(String Number);

  List<Room> findByDescription(String Description);

  List<Room> findByFloor(Floor id);

  @Query("SELECT new com.example.buildingmanagement.dtos.RoomResponseDTO(r.roomId, r.number, r.description, f.floorId, f.description, b.buildingId, b.name, b.address) " +
    "FROM Room r " +
    "JOIN r.floor f " +
    "JOIN f.building b")
  List<RoomResponseDTO> findRoomsWithBuildingInfo();
}

