package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_project
 * 表注释：项目表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_project")
public class Project {
    @Id
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 项目名
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目编号
     */
    @Column(name = "project_code")
    private String projectCode;
}