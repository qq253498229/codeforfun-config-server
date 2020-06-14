package cn.codeforfun.modules.app;

import cn.codeforfun.generator.mapper.AppMapper;
import cn.codeforfun.generator.model.App;
import cn.codeforfun.modules.app.vo.AppVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/app")
public class AppController {
    @Resource
    private AppMapper appMapper;

    @GetMapping
    public PageInfo<App> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(appMapper.selectAll());
    }

    @PostMapping
    public void save(@RequestBody @Valid AppVO appVO) {
        if (appVO.getAppId() == null) {
            appMapper.insertSelective(appVO);
        } else {
            appMapper.updateByPrimaryKeySelective(appVO);
        }
    }

    @GetMapping("/{id}")
    public App getOne(@PathVariable Integer id) {
        return appMapper.selectByPrimaryKey(id);
    }
}
