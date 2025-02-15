FROM adoptopenjdk/openjdk11:alpine-jre  as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM adoptopenjdk/openjdk11:alpine-jre 
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
RUN true 
#https://github.com/moby/moby/issues/37965
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]