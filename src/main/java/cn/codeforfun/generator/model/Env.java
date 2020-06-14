package cn.codeforfun.generator.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "project_id")
    private Long projectId;
}