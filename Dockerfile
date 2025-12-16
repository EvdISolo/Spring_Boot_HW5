FROM eclipse-temurin:8-jdk
EXPOSE 8081
ADD target/SpringBoot_HW5-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
