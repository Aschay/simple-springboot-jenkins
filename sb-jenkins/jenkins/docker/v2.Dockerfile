FROM maven:3.6.3-jdk-11-slim AS builder
RUN mkdir /sb-project
COPY . /sb-project
WORKDIR /sb-project
RUN  mvn clean package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre 
RUN apk add dumb-init
RUN mkdir /sb-project
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=builder /sb-project/target/*.jar /sb-project/app.jar
WORKDIR /sb-project
RUN chown -R javauser:javauser /sb-project
USER javauser
EXPOSE 8000
ENTRYPOINT ["dumb-init" ,"java","-jar", "app.jar"]