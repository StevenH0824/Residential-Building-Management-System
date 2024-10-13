import org.gradle.api.JavaVersion.VERSION_17

plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

//repositories {
//	mavenCentral()
//}


allprojects{
	group = "com.example"
//	java.sourceCompatibility = VERSION_17

	repositories{
		mavenCentral()
	}
}

subprojects{
	apply(plugin = "java")
	apply(plugin = "idea")
	apply(plugin = "io.spring.dependency-management")

	dependencyManagement{
		dependencies{

		}
	}
}



tasks.withType<Test> {
	useJUnitPlatform()
}


