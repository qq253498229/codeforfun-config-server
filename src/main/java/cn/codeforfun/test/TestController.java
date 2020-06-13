package cn.codeforfun.test;

import cn.codeforfun.generator.mapper.ApplicationMapper;
import cn.codeforfun.generator.model.Application;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private ApplicationMapper applicationMapper;

    @GetMapping("/test")
    public PageInfo<Application> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(applicationMapper.selectAll());
    }


}
