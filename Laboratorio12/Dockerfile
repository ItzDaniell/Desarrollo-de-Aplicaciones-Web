FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

RUN mkdir -p /data

EXPOSE 8081

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]