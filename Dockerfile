FROM java:8
VOLUME /tmp/tomcat
#ADD springboot-0.0.1-SNAPSHOT.jar spring-boot-docker.jar
COPY target/*.jar spring-boot-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-docker.jar"]
EXPOSE 8009

