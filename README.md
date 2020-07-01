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
DISCOVERY_TYPE | url | 注册中心类型，url/eureka/consul，其中 url 表示不使用注册中心
CONSUL_TOKEN | B595BC8E-DE44-4510-82D7-ECF5657F4D4D | 当 DISCOVERY_TYPE 为 consul 时生效，表示 consul的 acl_token
CONSUL_HOST | 8500 | 当 DISCOVERY_TYPE 为 consul 时生效，表示 consul的地址
CONSUL_PORT | 8500 | 当 DISCOVERY_TYPE 为 consul 时生效，表示 consul的端口号
EUREKA_SERVICE_URL | http://admin:admin@localhost:8761/eureka/ | 当 DISCOVERY_TYPE 为 eureka 时生效，表示 eureka 的注册地址
MONITOR_TYPE | none | 通知推送类型，none/rabbitmq，其中 none 表示不使用通知推送功能
RABBITMQ_HOST| localhost | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的地址
RABBITMQ_PORT| 5672 | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的端口号
RABBITMQ_USERNAME| admin | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的用户名
RABBITMQ_PASSWORD| admin | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的密码
RABBITMQ_EXCHANGE_NAME| config-server-exchange | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的交换机名称
RABBITMQ_QUEUE_NAME| config-server-queue | 当 MONITOR_TYPE 为 rabbitmq 时生效，表示 rabbitmq 的队列名称。最终队列名为：`${RABBITMQ_EXCHANGE_NAME}.${RABBITMQ_QUEUE_NAME}`

`start command`
```bash
docker run -d -p 8888:8888 --name config-server \
-e SERVER_PORT=8888 \
-e MYSQL_HOST=host.docker.internal -e MYSQL_PORT=3306 -e MYSQL_DATABASE=application_configuration \
-e MYSQL_USERNAME=root -e MYSQL_PASSWORD=root -e DISCOVERY_TYPE=url \
registry.cn-beijing.aliyuncs.com/codeforfun/config-server:1.0.4
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
    image: registry.cn-beijing.aliyuncs.com/codeforfun/config-server:1.0.4
    container_name: config-server
    environment:
      MYSQL_HOST: mysql
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