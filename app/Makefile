.PHONY: build run-dist clean checkstyle test setup

setup:
	chmod +x gradlew
	./gradlew clean build

build:
	chmod +x gradlew
	./gradlew build

clean:
	chmod +x gradlew
	./gradlew clean

checkstyle:
	chmod +x gradlew
	./gradlew checkstyleMain checkstyleTest

test:
	chmod +x gradlew
	./gradlew test

sonar:
	chmod +x gradlew
	./gradlew sonar

jacoco:
	chmod +x gradlew
	./gradlew jacocoTestReport

win-run-dist:
	.\build\install\app\bin\app

win-checkstyle:
	.\gradlew checkstyleMain checkstyleTest

win-test:
	.\gradlew test
