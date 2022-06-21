# distributed-systems lab

This repository contains a backend service implemented in Spring Boot.

## Setup

### Development
#### Maven
The project uses the buildtool maven and can be imported and used directly in an IDE.
The default application properties are suitable for a local development environment.

#### Docker
The included Dockerfile uses a maven image using Java 11 to build and a OpenJDK 11 image to run the build jar-file.

### Deployment
The stack can be deployed using docker compose.
The given docker-compose file tries to pull the latest version off dockerhub.
Alternatively, the current state can be built into an image.
A postgres database container is used.
The properties for the database connection are overwritten for the docker compose setup.

