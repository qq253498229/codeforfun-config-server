package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_config
 * 表注释：配置表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_config")
public class Config {
    @Id
    @Column(name = "config_id")
    @GeneratedValue(generator = "JDBC")
    private Long configId;

    /**
     * 配置名
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 环境id(外键)
     */
    @Column(name = "config_env_id")
    private Long configEnvId;
}