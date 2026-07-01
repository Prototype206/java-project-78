### Hexlet tests and linter status:
[![Actions Status](https://github.com/Prototype206/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Prototype206/java-project-78/actions)

### Java CI with Gradle:
[![Java CI](https://github.com/Prototype206/java-project-78/actions/workflows/ci.yml/badge.svg)](https://github.com/Prototype206/java-project-78/actions/workflows/ci.yml)

### SonarQube:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Prototype206_java-project-78&metric=alert_status)](https://sonarcloud.io/dashboard?id=Prototype206_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Prototype206_java-project-78&metric=coverage)](https://sonarcloud.io/dashboard?id=Prototype206_java-project-78)

---

# Валидатор данных

**Валидатор данных** — это компактная библиотека на Java для декларативной проверки корректности данных. Она предоставляет удобный Fluent API для настройки цепочек правил валидации строк, чисел и вложенных объектов Map.

---

## Требования
* **Java:** OpenJDK 21
* **Gradle:** не ниже 8.7

---

## Примеры использования

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

var v = new Validator();
StringSchema schema = v.string().required().minLength(5);

schema.isValid("hexlet"); // true
schema.isValid("");       // false

## Запуск и разработка

Все команды автоматизации выполняются из директории `app/`. Перед запуском перейдите в неё:

```bash
cd app

### Основные команды (Makefile)
* **Сборка проекта:** `make build`
* **Очистка сборки:** `make clean`
* **Проверка стиля кода (Checkstyle):** `make checkstyle`
* **Запуск тестов и генерация покрытия Jacoco:** `make test`