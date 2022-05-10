   FROM tomcat:8.5-jdk8-openjdk-slim
    RUN apt update && apt install -y \
        maven
    RUN git clone https://github.com/DerIngo/ShowHeaders.git
WORKDIR ShowHeaders
    RUN mvn package
WORKDIR $CATALINA_HOME
    RUN mv ShowHeaders/target/ROOT.war webapps
 EXPOSE 8080
    CMD ["catalina.sh", "run"]