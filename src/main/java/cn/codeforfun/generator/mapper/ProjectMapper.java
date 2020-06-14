package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.Project;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectMapper extends Mapper<Project> {
    void deleteAppConfigRelationshipByProjectId(Long id);

    void deletePropertyByProjectId(Long id);

    void deleteConfigByProjectId(Long id);
}