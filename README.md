# Coherent Solutions Challenge - Simple Backend for Hotel reservations
Create a backend application for hotel to work with reservations.

The objective of this project is to create a simple backend for hotel reservations, aligning as closely as possible with the instructions. However, I also want to mention that I made a couple of decisions that don't fully comply with the given requirements but have a valid reason behind them.

## Business requirements
The three points of the business requirements are perfectly met, which are:

- Create a new reservation.
- Read all reservations.
- Modify certain values of an existing reservation by ID.

## Technical requirements
**The Java version used: 8**

**Build automation tool: Maven** (However, I also want to mention that I am proficient in Gradle).

**Framework selected: Spring Boot 2.7.11**.

#### DATABASE

For the database, it is suggested to use an in-memory Set. However, I consider that in a real business environment, it is difficult to see but not impossible. It always depends on the business requirements. 

In my case, I preferred to implement **H2** (an in-memory database for development purposes) and a **data.sql** file to load information into this database.

**NOTE:** If I had chosen to use the Set<Reservation> option, I would have used a file located in the resources folder to simulate persistence in a database. 

Upon application startup, the file would be read to load all the information into the set. When creating, modifying, or deleting a resource, the file would have to be updated to keep the information up to date. Based on the requirements, I would use a HashMap implementation of the Set.

I want to emphasize that I decided to choose an in-memory database to avoid dealing with connection data or the actual installation of a database for easy testing of my implementation. I also believe it is more common to see this approach in the business world, with the help of SQL files to simulate a populated database upon application startup. 

However, if it were crucial to implement a different approach to excel in this challenge, I am willing to do it in a couple of days if allowed.
To conclude, my son broke his leg on Tuesday, so I could only invest a limited amount of time in this challenge. It was not because I didn't want to but rather due to taking care of my son in this unfortunate situation. Furthermore, I would have liked to develop the frontend in React JS, but I didn't have enough free time after work and taking care of my son for all of that.

## Work that was done

- This README.md file.
- Property files for DEV, QA, and PROD environments.
- Data Transfer Objects (DTOs) with their respective converters.
- An enumeration for messages used throughout the application.
- Request validators that help identify erroneous data before proceeding to transactional operations with the database.
- Formatters, which may seem unnecessary in this exercise, but when the application grows, they help maintain a consistent format for persisted information.
- Implemented a custom response (CustomResponse.class) for every response, indicating whether the request was successful or not, along with a message and the corresponding value.
- Added pagination for the API GET's methods.
- Swagger documentation for the application, which can be accessed at: [http://localhost:8087/api/v1/swagger-ui/index.html](http://localhost:8087/api/v1/swagger-ui/index.html)

## Work that needs to be done

- Implement security with Spring Security using JWT and roles.
- Unit testing.
- Frontend with React JS.

## Run this project

### Spring boot run: `mvn clean install spring-boot:run`
Runs the app in development mode.<br />
API: [http://localhost:8087/api/v1/swagger-ui/index.html](http://localhost:8087/api/v1/swagger-ui/index.html) to access the api endpoints.

H2 Console: [http://localhost:8087/api/v1/h2-console](http://localhost:8087/api/v1/h2-console)

Properties to access:
```
spring.datasource.url=jdbc:h2:mem:hoteldb
spring.datasource.username=rootUser
spring.datasource.password=
```

## Farewell
Thank you very much for the opportunity to participate, and I hope this small project briefly showcases my work and practices. I am very enthusiastic about this job, and although I couldn't dedicate much time due to my current responsibilities at work and as a father with my wife and children, I want to make it clear that I put forth my best effort and dedication into this small project.

Regards

Edgar Omar Lopez Hernandez

