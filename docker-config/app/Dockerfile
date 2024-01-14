# Use an image from Maven to build the project
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the project
RUN mvn package

# Use an image from Tomcat to run the project
FROM tomcat:9.0-jdk11-openjdk

# Copy the war file
COPY --from=builder /app/target/bring-global-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the port
EXPOSE 8080

# Run the project
CMD ["catalina.sh", "run"]
