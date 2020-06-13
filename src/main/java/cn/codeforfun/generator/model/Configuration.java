package cn.codeforfun.generator.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 表名：system_configuration
 * 表注释：配置表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_configuration")
public class Configuration {
    @Id
    @Column(name = "configuration_id")
    private Long configurationId;

    /**
     * 配置名
     */
    @Column(name = "configuration_name")
    private String configurationName;

    /**
     * 环境id(外键)
     */
    @Column(name = "environment_id")
    private Long environmentId;
}