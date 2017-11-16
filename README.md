# JWT Spring Security API Rest

## Requirements
This demo is build with with Maven 3, Java 1.8 and Postgres. 
You can run a postgress server with the currently required configuration trough the command bellow:

```
docker container run --name db --rm -p 5432:5432 -v /home/adr-fonseca/docker/docker-postgres/postgresql/:/var/lib/postgresql -e DB_USER=app -e DB_PASS=app -e DB_NAME=app adrianofonseca/postgres:9.5


```  


## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [http://localhost:8080](http://localhost:8080).

There are three user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:
```
Admin - admin:admin
User - user:password
Disabled - disabled:password (this user is disabled)
```

There are three endpoints that are reasonable for the demo:
```
/auth - authentication endpoint with unrestricted access
/persons - an example endpoint that is restricted to authorized users (a valid JWT token must be present in the request header)
/protected - an example endpoint that is restricted to authorized users with the role 'ROLE_ADMIN' (a valid JWT token must be present in the request header)
```

### Generating password hash for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords. Your can generate your hashes with this simple tool: [Bcrypt Generator](https://www.bcrypt-generator.com)

### Using another database

Actually this demo is using an embedded H2 database that is automatically configured by Spring Boot. If you want to connect to another database you have to specify the connection in the *application.yml* in the resource directory. Here is an example for a MySQL DB:

```
spring:
  jpa:
    hibernate:
      # possible values: validate | update | create | create-drop
      ddl-auto: create-drop
  datasource:
    url: jdbc:mysql://localhost/app
    username: app
    password: app
    driver-class-name: com.mysql.jdbc.Driver
```

## Accessing Swagger Documentatio

You can do that hitting the URL bellow locally.

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) 

## Copyright and license

The code is released under the [MIT license]