# JLearning
Project that is intended to help users to grasp new foreign languages.

### Technologies used:
- Amazon S3
- Spring Cloud
    - Service Registration and Discovery
    - Zuul routing
- Spring AOP, Security oAuth 2.0
- Gradle
- Angular 5
- Docker

### Additional information:
Project contains custom Spring Boot starter that defines security configuration for all dependent microservices:
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  jlearning.jwt.config.JwtConfig
```