#1 Create docker image with postgres db
docker build -t dataviewer-postgres-db ./

#2 Run image
docker run -d --name dataviewer-postgresdb-container -p 5432:5432 dataviewer-postgres-db
