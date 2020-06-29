package cn.codeforfun.modules.config.vo;

import cn.codeforfun.generator.model.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * @author wangbin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PropertyVO extends Property {
    /**
     * 属性名
     */
    @Column(name = "property_key")
    @NotBlank(message = "\"属性key\" 不能为空")
    private String propertyKey;
}
