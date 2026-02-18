Banking Application REST API (Spring Boot)

A simple Banking REST API built with Spring Boot and Spring Data JPA, designed to handle typical banking operations (create accounts, manage customers, transactions, etc.) with a clean layered architecture.

Repo: Banking_Application_Rest

Tech Stack

Java 25

Spring Boot 4.0.1

Spring Web MVC (REST APIs)

Spring Data JPA (ORM / DB layer)

MySQL Driver (mysql-connector-j)

Lombok (boilerplate reduction)

Maven + Maven Wrapper (mvnw)

What this project demonstrates

Building RESTful endpoints using Spring MVC

Persisting entities using Spring Data JPA

Database integration using MySQL

Clean separation of concerns (Controller → Service → Repository)

How to run locally
1) Prerequisites

Java 25

MySQL running locally (or a remote MySQL instance)

Maven (optional, because Maven Wrapper is included)

2) Configure database

Create a database (example):

CREATE DATABASE banking_app;


Then update your Spring Boot database config (usually in src/main/resources/application.properties or application.yml) with your credentials. Example:

spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3) Start the app

Using Maven Wrapper (recommended):

./mvnw spring-boot:run


Or on Windows:

mvnw.cmd spring-boot:run


The API will start on the default Spring Boot port (usually 8080) unless you changed it in config.

API Endpoints

This repo contains Spring MVC + JPA dependencies, but I couldn’t reliably extract the controller mappings from GitHub’s folder views in this environment.
If you paste your controller files here (or upload a ZIP), I’ll add an accurate endpoint table like:

GET /api/...

POST /api/...

PUT /api/...

DELETE /api/...

Project structure (typical)
src/
  main/
    java/        # controllers, services, repositories, entities
    resources/   # application.properties / application.yml

Tests

Test dependencies are included for JPA + Web MVC testing.
Run:

./mvnw test
