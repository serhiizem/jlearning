# JLearning
Project that is intended to help users to grasp new foreign languages.

#### Demo (creds: student student):
http://ec2-18-219-229-103.us-east-2.compute.amazonaws.com:8084

### Technologies used:
- Amazon S3, EC2, RDS
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
  JwtConfig
```
### Project architecture that allows access to databse in private subnet:
![database access architecture img](https://i.imgur.com/CJGmt61.jpg)
