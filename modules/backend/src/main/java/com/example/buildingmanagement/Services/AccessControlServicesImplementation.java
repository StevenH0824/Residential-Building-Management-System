//package com.example.buildingmanagement.Services;
//
//import com.example.buildingmanagement.Repositories.AccessControlRepository;
//import com.example.buildingmanagement.entities.AccessControl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//
//@Service
//public class AccessControlServicesImplementation {
//  @Autowired
//  private AccessControlRepository accessControlRepository;
//
//
//  public List<AccessControl> findByPersonLastName(String lastName) {
//    List<AccessControl> listOfAccessControls = accessControlRepository.findByPerson_LastName(lastName);
//    return listOfAccessControls.stream().toList();
//  }
//
//  public List<AccessControl> findByPerson_FirstName(String firstname) {
//    List<AccessControl> listOfAccessControls = accessControlRepository.findByPerson_FirstName(firstname);
//    return listOfAccessControls.stream().toList();
//  }
//
//  public List<AccessControl> findByPerson_PersonId(Long personId) {
//    List<AccessControl> listOfAccessControls = accessControlRepository.findByPerson_PersonId(personId);
//    return listOfAccessControls.stream().toList();
//  }
//
//  public List<AccessControl> findByControlGroup_ControlGroupId(Long controlGroupId) {
//    List<AccessControl> listOfAccessControls = accessControlRepository.findByControlGroup_ControlGroupId(controlGroupId);
//    return listOfAccessControls.stream().toList();
//  }
//}
