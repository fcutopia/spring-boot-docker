FROM java:8
VOLUME /tmp/tomcat
ADD springboot-0.0.1-SNAPSHOT.jar springboot-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-docker.jar"]

EXPOSE 8008