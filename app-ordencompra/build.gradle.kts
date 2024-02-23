plugins {
    id("java")
    id("io.quarkus") version "3.6.5"
    id("io.freefair.lombok") version "8.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val quarkusVersion = "3.6.5"

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:${quarkusVersion}"))

    implementation("io.quarkus:quarkus-arc") //CDI: Arc
    implementation("io.quarkus:quarkus-resteasy-reactive") //JAX-RS: RESTEasy
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson") //JAX-RS-Json: Jackson
    implementation("io.quarkus:quarkus-hibernate-orm-panache") //JPA: Hibernate
    implementation("io.quarkus:quarkus-jdbc-postgresql") //Driver JDBC: PostgreSQL

    //--rest client
    implementation("io.quarkus:quarkus-rest-client-reactive")
    implementation("io.quarkus:quarkus-rest-client-reactive-jackson")
    implementation("io.quarkus:quarkus-smallrye-health")
}

tasks.test {
    useJUnitPlatform()
}