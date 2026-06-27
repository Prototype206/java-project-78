plugins {
    id("java")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "7.3.1.8318"
}

group = "hexlet.code"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
