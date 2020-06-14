package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.App;
import cn.codeforfun.modules.app.vo.AppVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AppMapper extends Mapper<App> {
    List<AppVO> findAppList(@Param("projectId") Long projectId, @Param("appId") Long appId);

    void deleteAppConfigRelationshipByAppId(Long appId);

    void insertAppConfigRelationshipByAppId(@Param("appId") Long appId, @Param("configIdList") List<Long> configIdList);
}