FROM openjdk:17-jdk

WORKDIR /app

COPY target/movies-0.0.1-SNAPSHOT.jar /app/movie_renting.jar

EXPOSE 8080

CMD ["java", "-jar", "movie_renting.jar"]