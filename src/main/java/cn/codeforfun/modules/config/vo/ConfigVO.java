package cn.codeforfun.modules.config.vo;

import cn.codeforfun.generator.model.Config;
import cn.codeforfun.generator.model.Env;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigVO extends Config {
    private Env env;
}
