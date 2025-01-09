# Jobable
## Description
The application communicate with external server to fetch job offers for developers every 3h. <br>
After account creation you can sign in to the api using JWT. After that api allows you to show different offers, right now it is only possible to show all offers which have been fetched
and to show offer by id. You can add your own offer after siging in too.<br>
In this project I use modular monolith which allows transition to microservice architecture as the app continues to evolve. <br>
To add mongo db to the project I use docker desktop by writing docker-compose file.
## Architecture
![image](https://github.com/user-attachments/assets/b9fcfcb8-a519-4b66-bb67-4419c61cbf4d)

## Technologies 
- Java 
- Spring boot
- JWT Authentication
- Spring scheduler
- Validation
- RestTemplateS
- Redis
- Lombock
- Log4j2
- MongoDB + MongoExpress
- Docker
- Unit Test (Hamcrest, mockito)
- Integration test (wireMock, testcontainers, awaitility)
- Docker (dockerDesktop, docker-compose)
## Introduction the application work
![image](https://github.com/user-attachments/assets/080bc3c5-b0f8-4a1b-9427-e92e8b268a17)
![image](https://github.com/user-attachments/assets/81518abe-2920-4ae9-9dff-185714878e01)
![image](https://github.com/user-attachments/assets/bac27c16-29d9-4ab1-862f-fd41c5157dad)
![image](https://github.com/user-attachments/assets/b6112cfe-a967-46b8-bcef-e09c547e492e)




