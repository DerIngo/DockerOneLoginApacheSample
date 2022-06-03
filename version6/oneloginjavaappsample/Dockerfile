   FROM tomcat:8.5-jdk8-openjdk-slim
    RUN apt update && apt install -y \
        maven
   COPY pom.xml /app/pom.xml
WORKDIR /app
    RUN mvn package
   COPY . /app
    RUN mvn package
WORKDIR $CATALINA_HOME
    RUN mv /app/target/ROOT.war webapps
 EXPOSE 8080
    CMD ["catalina.sh", "run"]