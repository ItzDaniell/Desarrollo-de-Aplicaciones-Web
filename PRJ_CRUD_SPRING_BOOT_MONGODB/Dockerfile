# Usa una imagen ligera de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Crea un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el contenido del proyecto al contenedor
COPY . .

RUN chmod +x ./mvnw
# Compila el proyecto con Maven Wrapper, sin correr tests
RUN ./mvnw clean package -DskipTests

# Expone el puerto 8080 (puerto típico de Spring Boot)
EXPOSE 8087

# Ejecuta el archivo JAR generado por Maven
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]