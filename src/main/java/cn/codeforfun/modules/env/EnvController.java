package cn.codeforfun.modules.env;

import cn.codeforfun.generator.mapper.ConfigMapper;
import cn.codeforfun.generator.mapper.EnvMapper;
import cn.codeforfun.generator.model.Config;
import cn.codeforfun.generator.model.Env;
import cn.codeforfun.modules.env.vo.EnvVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import static cn.codeforfun.constant.BusinessConstant.CONTEXT_PATH;
import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_PROJECT_ID_NULL;

/**
 * @author wangbin
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/env")
@Validated
public class EnvController {
    @Resource
    private EnvMapper envMapper;
    @Resource
    private ConfigMapper configMapper;

    @GetMapping
    public PageInfo<Env> list(Pageable pageable, @NotNull(message = ERROR_MESSAGE_PROJECT_ID_NULL) Long projectId) {
        PageHelper.startPage(PageUtils.from(pageable));
        Example example = new Example(Env.class);
        example.createCriteria().andEqualTo("projectId", projectId);
        return new PageInfo<>(envMapper.selectByExample(example));
    }

    @GetMapping("/findAll")
    public List<EnvVO> findAll(@NotNull(message = ERROR_MESSAGE_PROJECT_ID_NULL) Long projectId) {
        return envMapper.findEnvList(projectId);
    }

    @PostMapping
    public void save(@RequestBody @Valid EnvVO envVO) {
        if (envVO.getEnvId() == null) {
            envMapper.insertSelective(envVO);
        } else {
            envMapper.updateByPrimaryKeySelective(envVO);
        }
    }

    @GetMapping("/{id}")
    public Env getOne(@PathVariable Long id) {
        return envMapper.selectByPrimaryKey(id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        configMapper.deleteAppConfigRelationshipByEnvId(id);
        configMapper.deletePropertyByEnvId(id);
        configMapper.delete(new Config(null, null, id));
        envMapper.deleteByPrimaryKey(id);
    }
}
