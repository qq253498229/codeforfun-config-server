package cn.codeforfun.generator.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long configId;

    /**
     * 配置名
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 环境id(外键)
     */
    @Column(name = "env_id")
    private Long envId;
}