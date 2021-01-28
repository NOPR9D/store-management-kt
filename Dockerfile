FROM gradle:6.6.0-jdk11

ADD src src
ADD build.gradle.kts .
ADD settings.gradle.kts .

RUN gradle build

FROM adoptopenjdk/openjdk11:latest

COPY --from=0 /home/gradle/build/libs/storeManagement-0.0.1-SNAPSHOT.jar storeManagement.jar

EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -jar /storeManagement.jar