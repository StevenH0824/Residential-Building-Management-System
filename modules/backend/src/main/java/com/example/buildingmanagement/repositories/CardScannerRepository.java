package com.example.buildingmanagement.repositories;

import com.example.buildingmanagement.entities.CardScanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardScannerRepository extends JpaRepository<CardScanner,Long> {

}
