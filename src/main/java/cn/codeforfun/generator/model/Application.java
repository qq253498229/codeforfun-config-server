package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_application
 * 表注释：应用表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_application")
public class Application {
    @Id
    @Column(name = "application_id")
    private Long applicationId;

    /**
     * 应用名
     */
    @Column(name = "application_name")
    private String applicationName;

    /**
     * 应用编码
     */
    @Column(name = "application_code")
    private String applicationCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "project_id")
    private Long projectId;
}