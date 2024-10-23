package com.example.buildingmanagement.repository;

import com.example.buildingmanagement.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  // Add custom query methods if needed, e.g., findByEmail or findByFirstName
//  Person findByPersonId(Long id);
//  Optional<Person> findByFirstName(String firstName);
//  Optional<Person> findByLastName(String lastName);
//  Optional<Person> findByPhoneNumber(String phoneName);
  Person findByEmail(String email);
}
