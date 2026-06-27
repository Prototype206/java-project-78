plugins {
    id("java")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "7.3.1.8318"
}

group = "hexlet.code"
version = "1.0.0"

val junitVersion = "5.10.2"
val assertjVersion = "3.25.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}



tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

checkstyle {
    toolVersion = "12.1.2"
    configFile = file("config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property("sonar.projectKey", "Prototype206_java-project-78")
        property("sonar.organization", "prototypes-organization")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "${layout.buildDirectory.get()}/reports/jacoco/test/xml")
    }
}
