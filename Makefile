.PHONY: setup build clean checkstyle test sonar jacoco

setup:
	cd app && chmod +x gradlew && java -jar gradle/wrapper/gradle-wrapper.jar clean build

build:
	cd app && ./gradlew build

clean:
	cd app && ./gradlew clean

checkstyle:
	cd app && ./gradlew checkstyleMain checkstyleTest

test:
	cd app && ./gradlew test

sonar:
	cd app && ./gradlew sonar

jacoco:
	cd app && ./gradlew jacocoTestReport
