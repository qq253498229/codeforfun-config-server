package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_app
 * 表注释：应用表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_app")
public class App {
    @Id
    @Column(name = "app_id")
    @GeneratedValue(generator = "JDBC")
    private Long appId;

    /**
     * 应用名
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 应用编码
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "project_id")
    private Long projectId;
}