package com.example.buildingmanagement.Repositories;


import com.example.buildingmanagement.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository <Person, Long>{
  Person findByPersonId(Long id);
  Optional<Person> findByFirstName(String firstName);
  Optional<Person> findByLastName(String lastName);
  Optional<Person> findByPhoneNumber(String phoneName);

}
