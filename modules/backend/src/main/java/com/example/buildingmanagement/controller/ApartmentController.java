package com.example.buildingmanagement.controller;

import com.example.buildingmanagement.entities.Apartment;
import com.example.buildingmanagement.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/apartments")
@RestController
public class ApartmentController {
  @Autowired
  private ApartmentService apartmentService;

  @GetMapping
  public List<Apartment> getAllApartments() {
    return apartmentService.getAllApartments();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
    Apartment apartment = apartmentService.getApartmentById(id);
    return apartment != null ? ResponseEntity.ok(apartment) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public Apartment createApartment(@RequestBody Apartment apartment) {
    return apartmentService.saveApartment(apartment);
  }

  @DeleteMapping("/{id}")
  public void deleteApartment(@PathVariable Long id) {
    apartmentService.deleteApartment(id);
  }
}
