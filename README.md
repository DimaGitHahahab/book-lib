# book-lib

This is a library management REST API with CRUD operations, authentication and authorization written with Spring Boot and PostgreSQL.

## Features

1. Sign up and login with JWT stored in cookie 
2. Manage book authors' information. 
3. Manage books information such as release date, ISBN, etc. 

## Deployment

Clone this repository:
```
git clone https://github.com/DimaGitHahahab/book-lib.git
```
Use provided Dockerfile and docker compose file to run the app with PostgreSQL:
```
docker compose up --build
```
All the additional configurations can be made in application.properties and docker files.

## Docs

Endpoints and their description can be seen in swagger ui:
```
http://localhost:8080/swagger-ui/index.html/
```