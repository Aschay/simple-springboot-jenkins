FROM adoptopenjdk/openjdk11:alpine-jre 
WORKDIR /opt/app 
RUN addgroup -S user && adduser -S user -G user
USER user
ARG JAR_FILE=/target/*.jar 
COPY ${JAR_FILE} app.jar 
EXPOSE 8000 
ENTRYPOINT ["java", "-jar", "app.jar"]