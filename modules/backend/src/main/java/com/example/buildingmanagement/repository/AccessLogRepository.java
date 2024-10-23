package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog,Long>{
}
