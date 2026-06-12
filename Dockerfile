FROM maven:3.9.6-ecclipse-temurin-21
WORKDIR /app
COPY ..
RUN mvn clean test -DskipTests
CMD["mvn","test"]