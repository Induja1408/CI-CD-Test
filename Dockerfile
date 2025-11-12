# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

# Download deps first for faster incremental builds
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline

# Now add sources and build
COPY src ./src
RUN mvn -B -q -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar (any name) from the build stage
COPY --from=build /build/target/*.jar /app/app.jar
EXPOSE 8080
# If your app is a CLI or server with a main() entrypoint:
ENTRYPOINT ["java","-jar","/app/app.jar"]
# If it’s just a library with tests and no main(), comment the ENTRYPOINT out.
