package cn.codeforfun.modules.config;

import cn.codeforfun.generator.mapper.ConfigMapper;
import cn.codeforfun.generator.model.Config;
import cn.codeforfun.modules.config.vo.ConfigVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/conf")
public class ConfigController {
    @Resource
    private ConfigMapper configMapper;

    @GetMapping
    public PageInfo<Config> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(configMapper.selectAll());
    }

    @PostMapping
    public void save(@RequestBody @Valid ConfigVO configVO) {
        if (configVO.getConfigId() == null) {
            configMapper.insertSelective(configVO);
        } else {
            configMapper.updateByPrimaryKeySelective(configVO);
        }
    }

    @GetMapping("/{id}")
    public Config getOne(@PathVariable Integer id) {
        return configMapper.selectByPrimaryKey(id);
    }
}
