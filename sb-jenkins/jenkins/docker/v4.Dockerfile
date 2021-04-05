FROM adoptopenjdk/openjdk11:jdk11u-alpine-nightly-slim as javabuilder
WORKDIR /opt/app 
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN  ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar) && ls -a 

FROM adoptopenjdk/openjdk11:jdk11u-alpine-nightly-slim 
RUN addgroup -S user && adduser -S user -G user
USER user
VOLUME /tmp
ARG DEPENDENCY=/opt/app/target/dependency
COPY --from=javabuilder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=javabuilder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=javabuilder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.sbjenkins.SbJenkinsApplication"]