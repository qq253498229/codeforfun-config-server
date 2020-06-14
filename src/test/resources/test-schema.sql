create table system_project
(
    project_id   bigint auto_increment
        primary key,
    project_name varchar(200) not null comment '项目名',
    project_code varchar(200) not null comment '项目编号',
    constraint system_project_project_code_uindex
        unique (project_code)
);

create table system_app
(
    app_id     bigint auto_increment
        primary key,
    app_name   varchar(200) not null comment '应用名',
    app_code   varchar(200) not null comment '应用编码',
    project_id bigint       not null comment '项目id(外键)',
    constraint system_app_app_code_uindex
        unique (app_code),
    constraint system_app_project_id_fk
        foreign key (project_id) references system_project (project_id)
);

create table system_env
(
    env_id     bigint auto_increment
        primary key,
    env_name   varchar(200) not null comment '环境名',
    env_code   varchar(200) not null comment '环境编号',
    project_id bigint       not null comment '项目id(外键)',
    constraint system_env_env_code_uindex
        unique (env_code),
    constraint system_env_project_id_fk
        foreign key (project_id) references system_project (project_id)
);

create table system_config
(
    config_id   bigint auto_increment
        primary key,
    config_name varchar(200) not null comment '配置名',
    env_id      bigint       not null comment '环境id(外键)',
    constraint system_config_env_id_fk
        foreign key (env_id) references system_env (env_id)
);

create table relationship_app_config
(
    app_id    bigint not null,
    config_id bigint not null,
    primary key (config_id, app_id),
    constraint relationship_app_config_app_id_fk
        foreign key (app_id) references system_app (app_id),
    constraint relationship_app_config_config_id_fk
        foreign key (config_id) references system_config (config_id)
);

create table system_property
(
    property_id    bigint auto_increment
        primary key,
    property_key   varchar(200) not null comment '属性名',
    property_value varchar(200) null comment '属性值',
    property_type  varchar(50)  null comment 'STRING,INTEGER,BOOLEAN',
    config_id      bigint       not null comment '配置id(外键)',
    constraint system_property_config_id_fk
        foreign key (config_id) references system_config (config_id)
);

