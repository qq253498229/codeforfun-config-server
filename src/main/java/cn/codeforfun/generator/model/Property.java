package cn.codeforfun.generator.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：system_property
 * 表注释：属性表
*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "system_property")
public class Property {
    @Id
    @Column(name = "property_id")
    @GeneratedValue(generator = "JDBC")
    private Long propertyId;

    /**
     * 属性名
     */
    @Column(name = "property_key")
    private String propertyKey;

    /**
     * 属性值
     */
    @Column(name = "property_value")
    private String propertyValue;

    /**
     * STRING,INTEGER,BOOLEAN
     */
    @Column(name = "property_type")
    private String propertyType;

    /**
     * 配置id(外键)
     */
    @Column(name = "property_config_id")
    private Long propertyConfigId;
}