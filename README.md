# Cards Challenge

## Description
This is a little project where I would like to demonstrate my knowledge building applications in Java. 
The goal of the project is building something well-designed and run a microservice for manage a card system

### Features
1. A card is identified by:
* Brand
* Card number
* Cardholder
* Expiry date
2. An operation is valid if the consumption is less than $1000.
3. A card is valid for operations if the expiration date is after the current day.
4. There are 3 card brands: Visa, NARA, and AMEX. Each brand has a different interest rate calculation:
* Visa: year/month
* NARA: day of the month * 0.5
* AMEX: month * 0.1

### Exercises
Based on the above features, consider the following exercises:
1. Query and persist the card in a database.
2. Report whether an operation is valid.
3. Report whether a card is valid for operations.
4. Obtain the interest rate for an operation.

### API Exercises
1. Implement an API to query the above points.
2. Deploy the solution to a hosting service.
3. Integrate the solution with a relational database.

## Hexagonal architecture and dependency injection
For this project I designed an DDD core, you can find it in `src/main/java/com/rlgino/CardsService/domain`. Into the domain folder you can find every component and class for the cards domain.
This design make easier use TDD because you can know about the every domain component.

## Testing by yourself
```shell
curl --location 'localhost:8080/card' \
--header 'Content-Type: application/json' \
--data '{
    "cardNumber": "1234123412341234",
    "brand": "VISA",
    "name": "Test",
    "lastName": "Pepe",
    "date": "05/2028"
}'
```

## Why Java?
In this case I'm using Java because it's one of the most known object-oriented programming language, which is mature and has lot of facilities. In addition, Spring boot offers a big
stack for building API and applications with all the SDK.

## End-points documentation
Define an API documentation (with Open API). 
For this case, I added a new library for document API end-points, the dependency `springdoc-openapi-starter-webmvc-ui` will help to generate the OAS document.
You can see the generated documentation in `http://localhost:8080/swagger-ui/index.html`. Or you can see the swagger-oas.json file in `http://localhost:8080/api-docs`

## Security implementation
TBD

## Error handling
TBD

## Database conection
We're connecting to the Database in our infrastructure folder. You can see the `BeanConfiguration.java` file and look for `cardRepositoryPostgres` injection.
In the future, the plan is adding MongoDB injection for changing the DB implementation.

## Testing
The application has a suite of testing complete with Docker integration:
* In `acceptance` tests you can find the API testing.
* In `application` tests you can find the domain testing and every use case test.
* In `infrastructure` tests you can find every test integration with Docker.

## Performance improving

Optimize system performance by identifying and addressing bottlenecks. Employ techniques such as caching and database indexing.

## Monitoring:

Implement effective logging systems (log4j) to track events and errors. Use monitoring tools (GCP monitoring or grafana monitoring) to oversee system performance and availability.

## Scalability:

Design the system to be horizontally (with TDD and Injection Dependency) and vertically scalable as needed. Consider implementing automatic scalability techniques (TBD).

## Maintenance:

Establish a maintenance plan to apply updates and security fixes regularly. Ensure that the code is maintainable in the long term.
* At this stage, the proposal plan is getting maintenance every one month, keeping update every dependency and adding new features.

## Protocol buffer integration

For protobuf integration you have [to install protobuf](https://github.com/protocolbuffers/protobuf/releases).
Then add the next dependency:
````groovy
implementation 'com.google.protobuf:protobuf-java:3.25.1'
````
You need to generate protobuf files, you can get that running the next code: 
`protoc -I=. --java_out=. com/rlgino/CardsService/infrastructure/usersproto/users.proto` 
in the directory `src/main/java/`

## Associated Repositories
[Java Cards service](https://github.com/rlgino/java-cards-services)
[Go User Service](https://github.com/rlgino/go-users-service)

## Tools and techniques
* TDD
* Hexagonal Architecture
* Java + JUnit
* DDDs

## Sources
* [Integration Testing by Baeldung](https://www.baeldung.com/integration-testing-in-spring)
* [DB Integration Tests with Spring](https://www.baeldung.com/spring-boot-testcontainers-integration-test)
* [Setup Spring Boot Database Integration Tests With Testcontainers](https://medium.com/tech-takeaways/setup-spring-boot-database-integration-tests-with-testcontainers-e578ced929)
* [Spring Boot + Swagger 3 example (with OpenAPI 3) - BezKoder](https://www.bezkoder.com/spring-boot-swagger-3/)
* [Introduction to Google Protocol Buffer](https://www.baeldung.com/google-protocol-buffer)

### Next steps:
Adding protobuf integration...