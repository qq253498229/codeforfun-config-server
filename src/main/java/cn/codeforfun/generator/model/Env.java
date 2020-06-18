package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_env
 * 表注释：环境表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_env")
public class Env {
    @Id
    @Column(name = "env_id")
    @GeneratedValue(generator = "JDBC")
    private Long envId;

    /**
     * 环境名
     */
    @Column(name = "env_name")
    private String envName;

    /**
     * 环境编号
     */
    @Column(name = "env_code")
    private String envCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "env_project_id")
    private Long envProjectId;
}