# Conventions

In order to maintain a consistent and understandable codebase, project follows certain conventions.

## Naming Conventions

Proper naming ensures that our code is readable and understandable by anyone who might read it, now or in the future. Here are the specific naming conventions we follow:

- **Endpoints and Controllers**: These are named using **plural nouns** to indicate that they handle collections of resources.\
  Example:
  ```
  Endpoint: `/users` (not `/user`)
  Controller: `UsersController` (not `UserController`)
  ```

##Project structure



#1 Create docker image with postgres db
docker build -t dataviewer-postgres-db ./

#2 Run image
docker run -d --name dataviewer-postgresdb-container -p 5432:5432 dataviewer-postgres-db
