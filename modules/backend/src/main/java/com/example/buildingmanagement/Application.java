package com.example.buildingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.buildingmanagement.repository")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
      // http://localhost:8080/swagger-ui/index.html#/
    }

}
