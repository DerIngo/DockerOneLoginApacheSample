   FROM tomcat:8.5-jdk8-openjdk-slim
    RUN apt update && apt install -y \
        maven
   COPY pom.xml /app/pom.xml
WORKDIR /app
    RUN mvn package
   COPY . /app
    RUN mvn package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true
WORKDIR $CATALINA_HOME
    RUN mv /app/target/ROOT.war webapps
 EXPOSE 8080
    CMD ["catalina.sh", "run"]