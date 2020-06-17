FROM maven:3-jdk-8-alpine
WORKDIR /app
COPY pom.xml ./pom.xml
RUN mvn package -Dmaven.test.skip=true -Palibaba-maven-repository
COPY src ./src
RUN mvn package -Dmaven.test.skip=true -Palibaba-maven-repository

FROM openjdk:8-jre-alpine
WORKDIR /app
ENV TZ=Asia/Shanghai
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk add tzdata \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

COPY --from=0 /app/target/app.jar ./app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
