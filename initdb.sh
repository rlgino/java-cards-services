docker run --name cards-challenge-db -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_USER=postgres -e POSTGRES_DB=testdb -p 5432:5432 -d postgres:11.1
