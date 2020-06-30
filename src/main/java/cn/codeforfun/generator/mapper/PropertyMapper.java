package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.Property;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PropertyMapper extends Mapper<Property> {
    List<Property> find(@Param("projectName") String projectName, @Param("appName") String appName, @Param("profiles") String[] profiles);

    void deleteByConfigId(@Param("configId") Long configId);
}