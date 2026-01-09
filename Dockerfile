# ====== BUILD STAGE ======
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos solo lo necesario para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código y construimos el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ====== RUNTIME STAGE ======
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el JAR desde el stage de build
COPY --from=build /app/target/*.jar app.jar

# Puerto típico de Spring Boot
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]