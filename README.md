# This repo delves deep into microservices core concepts, design patterns etc. using Spring Boot2, Spring WebFlux, Spring cloud, Docker. There are four microservices
product-composite-service
product-service
recommendation-service
review-service

product-composite-service invokes core microservices (product-service, recommendation-service, review-service) and aggregates the information returned. 

Tech stack:
Spring Boot 2.3.4
Spring Webflux
Log4j
Gradle
-----------------------------------------------
Docker
-----------------------------------------------
Dockerize all four microservices by adding Docker file and new Spring profile
Use Docker compose to build and start all four microservices
Create Intergartion tests to start and stop microservices automatically

