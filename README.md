<h1 align="center">
  RabbitMQ - Spring Boot Services
</h1>

<p align="center">
  <a href="#concept">Concept</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#routes">Routes</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#technologies">Technologies</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#building">Building</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#docker">Docker</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#contributing">Contributing</a>
</p>


## Concept
<img src="public/1.png">


## Routes
| Method | URL                                                | Description     |
|--------|----------------------------------------------------|-----------------|
| POST   | http://localhost:8080/api/v1/users/create          | Create User     |
| POST   | http://localhost:8080/api/v1/checkout              | Make Checkout   |
| GET    | http://localhost:8080/api/v1/users/{userId}        | Get User By Id  |
| GET    | http://localhost:8080/api/v1/users/{userId}/orders | Get User Orders |


### Resources Documentation
Make Checkout
```json
{
    "cardNumber": "1234123412341234",
    "cardName": "ERIC MAGALHAES",
    "cardExpireDate": "01/08/2030",
    "cardCvv": "984",
    "amount": 987.63,
    "cardType": "CREDIT",
    "userId": "f1e3973c-9423-4e42-9ca4-00e0f46a5186"
}
```

Create User
```json
{
  "firstName": "Éric",
  "lastName": "Magalhães",
  "email": "test@test.com"
}
```

RabbitMQ Management
<img src="public/2.jpg">


## Technologies
This project was developed using the following technologies:
- [Spring Boot](https://spring.io/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Validation](https://spring.io/projects)
- [MYSQL](https://www.mysql.com/)
- [Spring AMQP](https://spring.io/projects)
- [RabbitMQ]()
- [Model Mapper]()


## Building
You'll need [Java 11+](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed on your computer in order to build this app.
```bash
$ git clone https://github.com/eric-souzams/rabbitmq-spring-services.git
$ cd rabbitmq-spring-services

$ cd {service-name}
$ mvn install

after install all applications
$ cd {service-name}
$ mvn spring-boot:run
```


## Docker
You'll need [Docker](https://www.docker.com/) installed on your computer in order to build this app.
```bash
$ git clone https://github.com/eric-souzams/rabbitmq-spring-services.git
$ cd rabbitmq-spring-services
$ docker-compose up
```


## Contributing
This repository is currently under development. If you want to contribute please fork the repository and get your hands dirty, and make the changes as you'd like and submit the Pull request.