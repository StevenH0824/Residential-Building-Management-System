package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  // Add custom query methods if needed, e.g., findByEmail or findByFirstName
  Person findByPersonId(Long id);
  List<Person> findByFirstName(String firstName);
  List<Person> findByLastName(String lastName);
  List<Person> findByPhoneNumber(String phoneName);
  List<Person> findByEmail(String email);
}
