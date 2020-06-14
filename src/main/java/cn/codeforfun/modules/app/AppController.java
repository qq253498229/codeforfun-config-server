package cn.codeforfun.modules.app;

import cn.codeforfun.generator.model.Application;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app")
public class AppController {
    @Resource
    private AppMapper appMapper;

    @GetMapping
    public PageInfo<Application> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(appMapper.selectAll());
    }
}
