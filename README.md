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

### Running the project:

```bash
git clone https://github.com/serhiizem/jlearning.git
cd jlearning
```
Provide access information for connection to s3 bucket specifying *ACCESS_KEY_ID* and *SECRET_ACCESS_KEY* in  ./docker-compose.yml
```bash
./gradlew build
docker-compose up
```

### Additional information:
Project contains custom Spring Boot starter that defines security configuration for all dependent microservices:
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  jlearning.jwt.config.JwtConfig
```
