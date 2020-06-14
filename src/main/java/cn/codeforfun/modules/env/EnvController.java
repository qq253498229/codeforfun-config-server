package cn.codeforfun.modules.env;

import cn.codeforfun.generator.mapper.EnvMapper;
import cn.codeforfun.generator.model.Env;
import cn.codeforfun.modules.env.vo.EnvVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/env")
public class EnvController {
    @Resource
    private EnvMapper envMapper;

    @GetMapping
    public PageInfo<Env> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(envMapper.selectAll());
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
    public Env getOne(@PathVariable Integer id) {
        return envMapper.selectByPrimaryKey(id);
    }
}
