package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_environment
 * 表注释：环境表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_environment")
public class Environment {
    @Id
    @Column(name = "environment_id")
    private Long environmentId;

    /**
     * 环境名
     */
    @Column(name = "environment_name")
    private String environmentName;

    /**
     * 环境编号
     */
    @Column(name = "environment_code")
    private String environmentCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "project_id")
    private Long projectId;
}