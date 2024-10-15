package com.example.buildingmanagement.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry){
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:4200");
        /*
        Cross Origin method that says any method and any function is allowed on the origin that we provided, this is
        how we will connect it to our front end application.
         */
    }
}
