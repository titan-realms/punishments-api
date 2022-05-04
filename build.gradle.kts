plugins {
    java
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.freefair.lombok") version "6.4.3"
}

group = "net.titanrealms.api"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("redis.clients:jedis:4.2.2")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
