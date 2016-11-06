# blog-example-database-testing

Sample application which uses spring-boot and flyway for database setup with tests that uses
flyway to configure db and different database types on which tests can be run.

More details in [blog post](https://blog.pchudzik.com/201611/java-repository-testing/) related to this project.

To test application on h2 database:
```
./gradlew test
# or
./gradlew test -Dspring.profiles.active=dev,test
```

To test application on postgresql database:
```
./gradlew test -Dspring.profiles.active=postgres,test
```

To start postgres database there is read docker container which can be started:
```
docker run --name test-postgres -p 5432:5432 -e POSTGRES_PASSWORD=secretpassword -d postgres
```
