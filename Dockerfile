FROM java:8
VOLUME /tmp
RUN mkdir -p /app
ADD poly-function/build/libs/poly-function-0.0.2-SNAPSHOT.jar /app/app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar