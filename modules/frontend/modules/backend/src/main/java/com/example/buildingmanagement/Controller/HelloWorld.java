package com.example.buildingmanagement.Controller;

import com.example.buildingmanagement.DTOs.HelloWorldDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public HelloWorldDTO getHelloWorldMessage(){
        return new HelloWorldDTO("Hello Spring with Angular");
    }
}
