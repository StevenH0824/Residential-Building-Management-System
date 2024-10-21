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


  /*
  I will try to get rid of lombok towards the end of the project to see if it causes any issues, if not we are
  removing the lombok dependencies.
  The major benefits of reducing dependencies is that it makes our project faster to build, deploy, and download.
  It also reduces the application size
   */
  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  // Including swagger dependency to test out functionality of services in our project
  implementation("io.springfox:springfox-swagger-ui:3.0.0") // http://localhost:8080/swagger-ui/index.html#/


}





