//package com.example.buildingmanagement.repositories;
//
//import com.example.buildingmanagement.entities.AccessControlPerson;
//import com.example.buildingmanagement.entities.Person;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface AccessControlPersonRepository  extends JpaRepository<AccessControlPerson,Long> {
//  List<AccessControlPerson> findByAccessControl_AccessControlId(Long accessControlId);
//
//  List<AccessControlPerson> findByPersonId(Person personId);
//}
