FROM maven:3.6.3-jdk-11-slim AS mavenbuilder
RUN mkdir /sb-project
WORKDIR /sb-project
COPY pom.xml .
COPY src ./src
RUN  mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre  as javabuilder
COPY --from=mavenbuilder sb-project/target/*.jar  app.jar
RUN java -Djarmode=layertools -jar app.jar extract


FROM adoptopenjdk/openjdk11:alpine-jre 
COPY --from=javabuilder dependencies/ ./
COPY --from=javabuilder snapshot-dependencies/ ./
RUN true 
#https://github.com/moby/moby/issues/37965
COPY --from=javabuilder spring-boot-loader/ ./
COPY --from=javabuilder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

#docker run --name app -d  -p 8000:8000  custom_image 