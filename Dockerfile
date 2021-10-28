FROM bellsoft/liberica-openjdk-centos
ADD target/Conditional-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]