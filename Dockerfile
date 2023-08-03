FROM ubuntu:23.10

RUN apt-get update -y && \
    apt-get install -y openjdk-17-jdk

# The application's .jar file
ARG JAR_FILE=build/native/nativeCompile/main

# Add the application's .jar to the container
COPY ${JAR_FILE} /app
# RUN chmod +x ./app/main

# Run the app command
CMD ["./app"]