# forest-fire-monitor-backend

## Prerequisites
- JDK 11 with JAVA_HOME env variable set.
- PostgreSQL 9.5+ database installed

## How to prepare database
- Create user 'ffm-admin' with password 'ffm-admin' 
- Create empty database 'forest-fire-monitor-db' owned by 'ffm-admin'


## How to build
Run `.\mvnw package` command.

## How to run
Run `mvn spring-boot:run -Dspring-boot.run.profiles=dev` command.

## API docs & Swagger UI
API docs and Swagger UI are available under `http://localhost:8080/swagger-ui.html`