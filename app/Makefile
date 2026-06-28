.PHONY: build run-dist clean checkstyle test setup

# Утилита для установки прав на gradlew
define ensure_gradlew
	@if [ -f gradlew ]; then chmod +x gradlew; fi
endef

setup:
	$(call ensure_gradlew)
	./gradlew clean build

build:
	$(call ensure_gradlew)
	./gradlew build

clean:
	$(call ensure_gradlew)
	./gradlew clean

checkstyle:
	$(call ensure_gradlew)
	./gradlew checkstyleMain checkstyleTest

test:
	$(call ensure_gradlew)
	./gradlew test

sonar:
	$(call ensure_gradlew)
	./gradlew sonar

jacoco:
	$(call ensure_gradlew)
	./gradlew jacocoTestReport

win-run-dist:
	.\build\install\app\bin\app

win-checkstyle:
	.\gradlew checkstyleMain checkstyleTest

win-test:
	.\gradlew test