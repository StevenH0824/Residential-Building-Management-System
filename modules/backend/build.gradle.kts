plugins {
  id("java")
  // SpringBoot
  id("org.springframework.boot")
  id("io.spring.dependency-management")
}

tasks {
  bootJar {
    mainClass.set("com.example.buildingmanagement.Application")
  }
}

//tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
//  // Set the main class for the bootJar task
//  mainClass.set("com.example.buildingmanagement.Application")
//}

dependencies {
  // PostgreSQL
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  // latest version of postgreSQL according to the link https://mvnrepository.com/artifact/org.postgresql/postgresql
  implementation("org.postgresql:postgresql:42.7.4")
  implementation("org.springframework.boot:spring-boot-starter-data-rest")
  implementation("org.springframework.boot:spring-boot-starter-web")
  // fast in-memory database that we will be using before postgres
  runtimeOnly("com.h2database:h2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")
  // The dependency for mapper annotation
  implementation ("org.mapstruct:mapstruct:1.5.5.Final")
  annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.5.Final")
//  implementation("org.projectlombok:lombok:1.18.30")
//  annotationProcessor("org.projectlombok:lombok:1.18.30")
//  compileOnly("org.projectlombok:lombok:1.18.34")
//  annotationProcessor("org.projectlombok:lombok:1.18.34")
//  testCompileOnly("org.projectlombok:lombok:1.18.34")
//  testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
}



