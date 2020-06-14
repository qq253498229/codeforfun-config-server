package cn.codeforfun.modules.project;

import cn.codeforfun.generator.mapper.ProjectMapper;
import cn.codeforfun.generator.model.Project;
import cn.codeforfun.modules.project.vo.ProjectVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    private ProjectMapper projectMapper;

    @GetMapping
    public PageInfo<Project> list(Pageable pageable) {
        PageHelper.startPage(PageUtils.from(pageable));
        return new PageInfo<>(projectMapper.selectAll());
    }

    @PostMapping
    public void save(@RequestBody @Valid ProjectVO projectVO) {
        if (projectVO.getProjectId() == null) {
            projectMapper.insertSelective(projectVO);
        } else {
            projectMapper.updateByPrimaryKeySelective(projectVO);
        }
    }

    @GetMapping("/{id}")
    public Project getOne(@PathVariable Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        projectRepository.deleteById(id);
//    }

}
