package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.App;
import cn.codeforfun.modules.app.vo.AppVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AppMapper extends Mapper<App> {
    List<AppVO> findAppList();
}