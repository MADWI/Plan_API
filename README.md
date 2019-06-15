master: [![Build Status](https://travis-ci.org/MADWI/Schedule_API.svg?branch=master)](https://travis-ci.org/MADWI/Schedule_API)

# ZUT Schedule API 
REST API providing a schedule for West Pomeranian University of Technology

## Requirements
- JDK 8
- (optional) MySQL database

## Building
The project is built with Gradle. Run Gradle to build the project and to run the tests using the following command:

```bash
./gradlew [tasks-and-options]
```

To build the project run the following command:
```bash
./gradlew build
```

To run tests use:
```bash
./gradlew check
```

To create bootable JAR file run the command:
```bash
./gradlew bootJar
```
JAR file will be created in `build/libs/` directory named `Schedule_API-<version>.jar`

## Run environment variables
When using external database these environment variables can be set to define database credentials.

- `SCHEDULE_USER_NAME` - database login
- `SCHEDULE_USER_PASSWORD` - database password

## Running
To build and run application run the following command:
```bash
./gradlew bootRun
```

To run already build application having executable JAR run:
```bash
./Schedule_API-<version>.jar
```

Additional options can be specified when starting application using following commands:

When starting using Gradle:
```bash
./gradlew bootRun --args='--example.argument=value'
```
When starting using JAR:
```bash
./Schedule_API.jar --example.argument=value
```

Common arguments:
- `spring.profiles.active` - sets active environment profile. Available profiles:
    - `dev` (default)
    - `production`
- `spring.datasource.url` - full URI to MySQL database e.g.: `jdbc:mysql://localhost:3306/schedule?characterEncoding=utf-8`
- `server.port` - port of the server. Default: `8080`

## Api documentation
Swagger 2.0 documentation: 
http://plan.zut.edu.pl/api/swagger-ui.html
