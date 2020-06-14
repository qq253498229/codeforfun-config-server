package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.Config;
import cn.codeforfun.modules.config.vo.ConfigVO;
import tk.mybatis.mapper.common.Mapper;

public interface ConfigMapper extends Mapper<Config> {
    ConfigVO findOneFetchProperty(Long id);

    void deleteAppConfigRelationshipByEnvId(Long id);

    void deletePropertyByEnvId(Long id);
}