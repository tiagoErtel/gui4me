# -------------------------------
# Stage 0: Build React frontend
# -------------------------------
FROM node:20 AS frontend-build
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

# -------------------------------
# Stage 1: Build Spring Boot backend
# -------------------------------
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src
# Copia o build do React para resources/static
COPY --from=frontend-build /frontend/dist ./src/main/resources/static
RUN mvn clean package -DskipTests

# -------------------------------
# Stage 2: Run application
# -------------------------------
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

