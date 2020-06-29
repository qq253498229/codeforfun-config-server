package cn.codeforfun.modules.project;

import cn.codeforfun.base.BaseController;
import cn.codeforfun.generator.model.App;
import cn.codeforfun.generator.model.Env;
import cn.codeforfun.generator.model.Project;
import cn.codeforfun.modules.project.vo.ProjectVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.codeforfun.constant.BusinessConstant.CONTEXT_PATH;

/**
 * @author wangbin
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/project")
@Validated
public class ProjectController extends BaseController {

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
    public Project getOne(@PathVariable Long id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    @DeleteMapping("/{id}")
    @Transactional(rollbackFor = Exception.class)
    public void delete(@PathVariable Long id) {
        projectMapper.deleteAppConfigRelationshipByProjectId(id);
        projectMapper.deletePropertyByProjectId(id);
        projectMapper.deleteConfigByProjectId(id);
        envMapper.delete(new Env(null, null, null, id));
        appMapper.delete(new App(null, null, null, id));
        projectMapper.deleteByPrimaryKey(id);
    }
}
