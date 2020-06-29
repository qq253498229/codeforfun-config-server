package cn.codeforfun.base;

import cn.codeforfun.generator.mapper.*;

import javax.annotation.Resource;

/**
 * @author wangbin
 */
public abstract class BaseController {
    @Resource
    protected AppMapper appMapper;
    @Resource
    protected ConfigMapper configMapper;
    @Resource
    protected PropertyMapper propertyMapper;
    @Resource
    protected EnvMapper envMapper;
    @Resource
    protected ProjectMapper projectMapper;
}
