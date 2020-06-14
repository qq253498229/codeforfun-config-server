package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.Env;
import cn.codeforfun.modules.env.vo.EnvVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EnvMapper extends Mapper<Env> {
    List<EnvVO> findEnvList(Long projectId);
}