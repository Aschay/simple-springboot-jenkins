FROM adoptopenjdk/openjdk11:alpine-jre 
RUN addgroup -S user && adduser -S user -G user
USER user
ARG JAR_FILE=target/*.jar 
WORKDIR /opt/app 
COPY ${JAR_FILE} app.jar 
EXPOSE 8000 
ENTRYPOINT ["java", "-jar", "app.jar"]