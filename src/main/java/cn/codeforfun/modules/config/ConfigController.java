package cn.codeforfun.modules.config;

import cn.codeforfun.base.BaseController;
import cn.codeforfun.generator.model.Config;
import cn.codeforfun.generator.model.Property;
import cn.codeforfun.modules.config.vo.ConfigVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_ENV_ID_NULL;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/conf")
@Validated
public class ConfigController extends BaseController {

    @GetMapping
    public PageInfo<Config> list(Pageable pageable, @NotNull(message = ERROR_MESSAGE_ENV_ID_NULL) Long envId) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(configMapper.select(new Config(null, null, envId)));
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public void save(@RequestBody @Valid ConfigVO configVO) {
        configVO.setConfigEnvId(configVO.getEnvId());
        if (configVO.getConfigId() == null) {
            configMapper.insertSelective(configVO);
        } else {
            configMapper.updateByPrimaryKeySelective(configVO);
        }

        propertyMapper.deleteByConfigId(configVO.getConfigId());

        configVO.getPropertyList().forEach(p -> {
            p.setPropertyConfigId(configVO.getConfigId());
            propertyMapper.insertSelective(p);
        });
    }

    @GetMapping("/{id}")
    public ConfigVO getOne(@PathVariable Long id) {
        return configMapper.findOneFetchProperty(id);
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public void delete(@PathVariable Long id) {
        configMapper.deleteAppConfigRelationshipByConfigId(id);
        propertyMapper.delete(new Property(null, null, null, null, id));
        configMapper.deleteByPrimaryKey(id);
    }
}
