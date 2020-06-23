# codeforfun-config-server

文档地址: [Spring Cloud 配置中心](https://consolelog.gitee.io/docker-config-server-all-in-one/)

官方文档: [官方文档](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.2.3.RELEASE/reference/html/) 、 [中文版](https://consolelog.gitee.io/docs-config/)


参数名 | 默认值 | 说明
---|---|---
SERVER_PORT | 8888 | 后端暴露的端口
MYSQL_HOST | localhost | 数据库地址
MYSQL_PORT | 3306 | 数据库端口号
MYSQL_DATABASE | application_configuration | 数据库名
MYSQL_USERNAME | root | 数据库登录名
MYSQL_PASSWORD | root | 数据库登录密码
APPLICATION_NAME | config-server | SpringBoot中的 spring.application.name
DISCOVERY_TYPE | url | 注册中心类型，url/eureka/consul，其中url为不使用注册中心
CONSUL_TOKEN | B595BC8E-DE44-4510-82D7-ECF5657F4D4D | 当 DISCOVERY_TYPE 为 consul 时生效，consul的 acl_token
CONSUL_HOST | 8500 | 当 DISCOVERY_TYPE 为 consul 时生效，consul的地址
CONSUL_PORT | 8500 | 当 DISCOVERY_TYPE 为 consul 时生效，consul的端口号
EUREKA_SERVICE_URL | http://admin:admin@localhost:8761/eureka/ | 当 DISCOVERY_TYPE 为 eureka 时生效，eureka 的注册地址

`start command`
```bash
docker run -d -p 8888:8888 --name config-server \
-e SERVER_PORT=8888 \
-e MYSQL_HOST=host.docker.internal -e MYSQL_PORT=3306 -e MYSQL_DATABASE=application_configuration \
-e MYSQL_USERNAME=root -e MYSQL_PASSWORD=root -e DISCOVERY_TYPE=url \
registry.cn-beijing.aliyuncs.com/codeforfun/config-server:1.0.2
```

`stop command`
```bash
docker rm -f config-server
```

`log command`
```bash
docker logs -f config-server
```

`docker-compose.yml`
```yaml
version: "3"
services:
  config-server:
    image: registry.cn-beijing.aliyuncs.com/codeforfun/config-server:1.0.2
    container_name: config-server
    environment:
      SERVER_PORT: 8888
      MYSQL_HOST: mysql
      MYSQL_PORT: 3306
      MYSQL_DATABASE: application_configuration
      MYSQL_USERNAME: root
      MYSQL_PASSWORD: root
      DISCOVERY_TYPE: url
    ports:
      - "8888:8888"
  mysql:
    image: mysql:8.0.20
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes:
      - "./schema.sql:/docker-entrypoint-initdb.d/schema.sql"
    ports:
      - "3306:3306"
```