# Stage 1: Build the Java Application (Gradle)
FROM gradle:8.13.0-jdk21 AS gradle-builder

# Set the working directory in the container
WORKDIR /backend

# Copy the Gradle files
COPY backend/build.gradle backend/settings.gradle /backend/
COPY backend/src  /backend/src

# Run the Gradle build (build the Java application)
RUN gradle uberJar --no-daemon

# Stage 2: Build the Node.js Application
FROM node:22 AS node-builder

# Set the working directory for Node.js
WORKDIR /frontend

# Copy the Node.js project files
COPY frontend/package.json frontend/package-lock.json /frontend/

# Install Node.js dependencies
RUN npm install

# Copy the rest of your Node.js application
COPY  frontend/index.html  frontend/vite.config.js /frontend/
COPY frontend/src /frontend/src

RUN npm run build

# Stage 3: Create the Final Image (JRE 21)
FROM eclipse-temurin:21.0.6_7-jre-ubi9-minimal

# Set working directory
WORKDIR /hello

# Copy the built Java application from the gradle-builder stage
COPY --from=gradle-builder /backend/build/libs/backend-uber-1.0-SNAPSHOT.jar /hello/app.jar

# Copy the react application from the node-builder stage
COPY --from=node-builder /frontend/dist /hello/static

COPY backend/config/application-docker.properties /hello/config/application.properties

# Expose tomcat port
EXPOSE 8080

#Run Tomcat embedded
CMD ["java", "-jar", "app.jar"]
