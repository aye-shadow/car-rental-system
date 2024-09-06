#!/bin/bash

# Clean and package the Maven project
mvn clean package
#mvn clean install -DskipTests

# Run the Java application
java -cp target/assignment1-1.0-SNAPSHOT.jar org.example.Main