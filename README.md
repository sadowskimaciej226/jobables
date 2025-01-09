# Jobable
## Description
The application communicate with external server to fetch job offers for developers every 3h. <br>
After account creation you can sign in to the api using JWT. After that api allows you to show different offers, right now it is only possible to show all offers which have been fetched
and to show offer by id. You can add your own offer after siging in too.<br>
In this project I use modular monolith which allows transition to microservice architecture as the app continues to evolve. <br>
To add mongo db to the project I use docker desktop by writing docker-compose file.
## Technologies 
- Java 
- Spring boot
- JWT Authentication
- Spring scheduler
- Validation
- RestTemplate
- Redis
- Lombock
- Log4j2
- MongoDB + MongoExpress
- Docker
- Unit Test (Hamcrest, mockito)
- Integration test (wireMock, testcontainers, awaitility)
- Docker (dockerDesktop, docker-compose)
## Introduction how to use it
