plugins {
    id("java")
    id("org.springframework.boot") version "2.6.4"
}

group = "com.sachdeva"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Test
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    //SpringBoot
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.4")

    //Logging
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.2.6")

    //CLI
    implementation("commons-cli:commons-cli:1.4")
}
tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}
tasks.test {
    useJUnitPlatform()
}