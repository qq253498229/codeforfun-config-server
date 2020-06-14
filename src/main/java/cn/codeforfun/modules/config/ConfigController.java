package cn.codeforfun.modules.config;

import cn.codeforfun.generator.mapper.ConfigMapper;
import cn.codeforfun.generator.mapper.PropertyMapper;
import cn.codeforfun.generator.model.Config;
import cn.codeforfun.generator.model.Property;
import cn.codeforfun.modules.config.vo.ConfigVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/conf")
public class ConfigController {
    @Resource
    private ConfigMapper configMapper;
    @Resource
    private PropertyMapper propertyMapper;

    @GetMapping
    public PageInfo<Config> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(configMapper.selectAll());
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public void save(@RequestBody @Valid ConfigVO configVO) {
        if (configVO.getConfigId() == null) {
            configMapper.insertSelective(configVO);
        } else {
            configMapper.updateByPrimaryKeySelective(configVO);
        }

        Example example = new Example(Property.class);
        example.createCriteria().andEqualTo("configId", configVO.getConfigId());
        propertyMapper.deleteByExample(example);

        configVO.getPropertyList().forEach(p -> {
            p.setConfigId(configVO.getConfigId());
            propertyMapper.insertSelective(p);
        });
    }

    @GetMapping("/{id}")
    public ConfigVO getOne(@PathVariable Long id) {
        return configMapper.findOneFetchProperty(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        configMapper.deleteAppConfigRelationshipByConfigId(id);
        propertyMapper.delete(new Property(null, null, null, null, id));
        configMapper.deleteByPrimaryKey(id);
    }
}
