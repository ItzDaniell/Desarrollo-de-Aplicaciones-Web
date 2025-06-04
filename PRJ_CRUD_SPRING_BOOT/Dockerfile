FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y ca-certificates && apt-get clean

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8087

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]