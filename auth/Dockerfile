FROM ghcr.io/graalvm/jdk:ol9-java17-22.3.0-b2 as JAR_EXTRACT
WORKDIR /app
ARG JAR_FILE=*.jar
COPY ./target/${JAR_FILE} ./app.jar
RUN java -Djarmode=layertools -jar ./app.jar extract

FROM ghcr.io/graalvm/jdk:ol9-java17-22.3.0-b2
WORKDIR /application
COPY --from=JAR_EXTRACT /app/dependencies ./
COPY --from=JAR_EXTRACT /app/spring-boot-loader ./
COPY --from=JAR_EXTRACT /app/snapshot-dependencies ./
COPY --from=JAR_EXTRACT /app/application ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
