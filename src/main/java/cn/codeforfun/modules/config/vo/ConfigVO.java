package cn.codeforfun.modules.config.vo;

import cn.codeforfun.generator.model.Configuration;
import cn.codeforfun.generator.model.Environment;
import lombok.Data;

@Data
public class ConfigVO {
    private Long configurationId;
    private Configuration config;
    private Environment env;
}
