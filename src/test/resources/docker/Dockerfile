FROM nginx:alpine as APPLICATION
COPY src/test/resources/docker/nginx/nginx.conf /etc/nginx

FROM --platform=linux/amd64 adoptopenjdk/openjdk11:jre-11.0.15_10-alpine

ARG JAR_FILE_NAME
RUN echo "$JAR_FILE_NAME"

RUN mkdir /app
COPY target/${JAR_FILE_NAME} /app/application.jar
COPY --from=APPLICATION /etc/nginx /etc/nginx

RUN apk add curl
RUN apk add nginx
# set default environment variables
ENV LANG C.UTF-8

EXPOSE 8080
ENTRYPOINT ["/bin/sh", "-c", "nginx -g 'daemon on;' && java -jar /app/application.jar"]
