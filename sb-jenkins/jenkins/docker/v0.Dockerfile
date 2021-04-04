FROM adoptopenjdk/openjdk11:alpine-jre 
RUN addgroup -S user && adduser -S user -G user
USER user
ARG JAR_FILE=target/*.Jar 
WORKDIR /opt/app 
COPY ${JAR_FILE} app.jar 
EXPOSE 8080 
ENTRYPOINT ["java", "- jar", "app.jar"]