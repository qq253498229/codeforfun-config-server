package cn.codeforfun.modules.config.vo;

import cn.codeforfun.generator.model.Config;
import cn.codeforfun.generator.model.Env;
import cn.codeforfun.generator.model.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_ENV_ID_NULL;

/**
 * @author wangbin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConfigVO extends Config {
    /**
     * 配置名
     */
    @Column(name = "config_name")
    @NotBlank(message = "\"配置名\" 不能为空")
    private String configName;
    /**
     * 环境id(外键)
     */
    @NotNull(message = ERROR_MESSAGE_ENV_ID_NULL)
    private Long envId;

    private List<@Valid Property> propertyList;

    private Env env;
}
