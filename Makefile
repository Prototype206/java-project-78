.PHONY: setup build clean checkstyle test sonar jacoco

setup:
	cd app && chmod +x gradlew && ./gradlew clean build

build:
	cd app && chmod +x gradlew && ./gradlew build

clean:
	cd app && chmod +x gradlew && ./gradlew clean

checkstyle:
	cd app && chmod +x gradlew && ./gradlew checkstyleMain checkstyleTest

test:
	cd app && chmod +x gradlew && ./gradlew test

sonar:
	cd app && chmod +x gradlew && ./gradlew sonar

jacoco:
	cd app && chmod +x gradlew && ./gradlew jacocoTestReport
