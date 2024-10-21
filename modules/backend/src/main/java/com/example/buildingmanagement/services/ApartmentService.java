package com.example.buildingmanagement.services;
import com.example.buildingmanagement.entities.Apartment;
import com.example.buildingmanagement.repositories.ApartmentRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApartmentService {

  @Autowired
  private ApartmentRepository apartmentRepository;

  public List<Apartment> getAllApartments() {
    return apartmentRepository.findAll();
  }

  public Apartment getApartmentById(Long id) {
    return apartmentRepository.findById(id).orElse(null);
  }

  public Apartment saveApartment(Apartment apartment) {
    return apartmentRepository.save(apartment);
  }

  public void deleteApartment(Long id) {
    apartmentRepository.deleteById(id);
  }
}
