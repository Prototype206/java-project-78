.PHONY: build run-dist clean checkstyle test setup

setup:
	chmod +x gradlew
	./gradlew clean build

build: setup
	./gradlew build

run-dist:
	./build/install/app/bin/app

clean:
	./gradlew clean

checkstyle:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

sonar:
	./gradlew sonar

jacoco:
	./gradlew jacocoTestReport

win-run-dist:
	.\build\install\app\bin\app

win-checkstyle:
	.\gradlew checkstyleMain checkstyleTest

win-test:
	.\gradlew test
