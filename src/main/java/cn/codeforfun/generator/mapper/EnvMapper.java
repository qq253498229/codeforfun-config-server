package cn.codeforfun.generator.mapper;

import cn.codeforfun.generator.model.Env;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EnvMapper extends Mapper<Env> {
    List<Env> findEnvList(Long projectId);
}