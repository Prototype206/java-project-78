setup:
	cd app && $(MAKE) setup

build:
	cd app && $(MAKE) build

clean:
	cd app && $(MAKE) clean

checkstyle:
	cd app && $(MAKE) checkstyle

test:
	cd app && $(MAKE) test

sonar:
	cd app && $(MAKE) sonar

jacoco:
	cd app && $(MAKE) jacoco
