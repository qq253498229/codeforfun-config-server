CREATE TABLE `relationship_application_configuration`
(
    `application_id`   bigint NOT NULL,
    `configuration_id` bigint NOT NULL
);
CREATE TABLE `system_application`
(
    `application_id`   bigint       NOT NULL auto_increment,
    `application_name` varchar(200) NOT NULL COMMENT '应用名',
    `application_code` varchar(200) NOT NULL COMMENT '应用编码',
    `project_id`       bigint       NOT NULL COMMENT '项目id(外键)',
    PRIMARY KEY (`application_id`),
    CONSTRAINT `system_application_code_uindex` UNIQUE (`application_code`)
);
CREATE TABLE `system_configuration`
(
    `configuration_id`   bigint       NOT NULL auto_increment,
    `configuration_name` varchar(200) NOT NULL COMMENT '配置名',
    `environment_id`     bigint       NOT NULL COMMENT '环境id(外键)',
    PRIMARY KEY (`configuration_id`)
);
CREATE TABLE `system_environment`
(
    `environment_id`   bigint       NOT NULL auto_increment,
    `environment_name` varchar(200) NOT NULL COMMENT '环境名',
    `environment_code` varchar(200) NOT NULL COMMENT '环境编号',
    `project_id`       bigint       NOT NULL COMMENT '项目id(外键)',
    PRIMARY KEY (`environment_id`),
    CONSTRAINT `system_environment_code_uindex` UNIQUE (`environment_code`)
);
CREATE TABLE `system_project`
(
    `project_id`   bigint       NOT NULL auto_increment,
    `project_name` varchar(200) NOT NULL COMMENT '项目名',
    `project_code` varchar(200) NOT NULL COMMENT '项目编号',
    PRIMARY KEY (`project_id`),
    CONSTRAINT `system_project_code_uindex` UNIQUE (`project_code`)
);
CREATE TABLE `system_property`
(
    `property_id`      bigint       NOT NULL auto_increment,
    `property_key`     varchar(200) NOT NULL COMMENT '属性名',
    `property_value`   varchar(200) DEFAULT NULL COMMENT '属性值',
    `property_type`    varchar(50)  DEFAULT NULL COMMENT 'STRING,INTEGER,BOOLEAN',
    `configuration_id` bigint       NOT NULL COMMENT '配置id(外键)',
    PRIMARY KEY (`property_id`)
);
