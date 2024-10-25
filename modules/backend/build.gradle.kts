plugins {
  id("java")
  id("org.springframework.boot")// Update to your current Spring Boot version
  id("io.spring.dependency-management") // Use the latest version
}

tasks {
  bootJar {
    mainClass.set("com.example.buildingmanagement.Application")
  }
}

dependencies {
  // Spring Boot starters
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-rest")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // Database dependencies
  runtimeOnly("org.postgresql:postgresql:42.7.4") // PostgreSQL
//  runtimeOnly("com.h2database:h2") // In-memory database

  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  // Swagger/OpenAPI dependencies
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
  implementation ("org.modelmapper:modelmapper:1.1.0")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
  testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")

}


// Ensure that source compatibility is set correctly
java {
  sourceCompatibility = JavaVersion.VERSION_17 // Set this to your preferred version
  targetCompatibility = JavaVersion.VERSION_17
}

// Optional: Set the version for test containers
tasks.withType<Test> {
  useJUnitPlatform() // Ensure JUnit 5 is used
}

//plugins {
//  id("java")
//  // SpringBoot
//  id("org.springframework.boot")
//  id("io.spring.dependency-management")
//}
//
//tasks {
//  bootJar {
//    mainClass.set("com.example.buildingmanagement.Application")
//  }
//}
//
////tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
////  // Set the main class for the bootJar task
////  mainClass.set("com.example.buildingmanagement.Application")
////}
//
//dependencies {
//  // PostgreSQL
//  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//  // latest version of postgreSQL according to the link https://mvnrepository.com/artifact/org.postgresql/postgresql
//  implementation("org.postgresql:postgresql:42.7.4")
//  implementation("org.springframework.boot:spring-boot-starter-data-rest")
//  implementation("org.springframework.boot:spring-boot-starter-web")
//  // fast in-memory database that we will be using before postgres
//  runtimeOnly("com.h2database:h2")
//  testImplementation("org.springframework.boot:spring-boot-starter-test")
//  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//
//
//  /*
//  I will try to get rid of lombok towards the end of the project to see if it causes any issues, if not we are
//  removing the lombok dependencies.
//  The major benefits of reducing dependencies is that it makes our project faster to build, deploy, and download.
//  It also reduces the application size
//   */
//  compileOnly("org.projectlombok:lombok")
//  annotationProcessor("org.projectlombok:lombok")
//
//  // Including swagger dependency to test out functionality of services in our project
//  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
////  implementation("io.springfox:springfox-swagger-ui:3.0.0") // http://localhost:8080/swagger-ui/index.html#/
//
//  // Adding the unit test into springboot
//  testImplementation("org.springframework.security:spring-security-test")
//  testImplementation("org.testcontainers:junit-jupiter")
//  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//}
//
//
//
//
//
