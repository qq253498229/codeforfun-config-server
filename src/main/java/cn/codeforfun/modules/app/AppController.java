package cn.codeforfun.modules.app;

import cn.codeforfun.generator.mapper.AppMapper;
import cn.codeforfun.generator.model.Config;
import cn.codeforfun.modules.app.vo.AppVO;
import cn.codeforfun.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static cn.codeforfun.constant.BusinessConstant.CONTEXT_PATH;
import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_PROJECT_ID_NULL;

/**
 * @author wangbin
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/app")
public class AppController {
    @Resource
    private AppMapper appMapper;

    @GetMapping
    public PageInfo<AppVO> list(Pageable pageable, @NotNull(message = ERROR_MESSAGE_PROJECT_ID_NULL) Long projectId) {
        PageHelper.startPage(PageUtils.from(pageable));
        List<AppVO> appList = appMapper.findAppList(projectId, null);
        return new PageInfo<>(appList);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public void save(@RequestBody @Valid AppVO appVO) {
        appVO.setAppProjectId(appVO.getProjectId());
        if (appVO.getAppId() == null) {
            appMapper.insertSelective(appVO);
        } else {
            appMapper.updateByPrimaryKeySelective(appVO);
        }
        Long appId = appVO.getAppId();
        appMapper.deleteAppConfigRelationshipByAppId(appId);
        List<Long> configIdList = appVO.getConfigList().stream().map(Config::getConfigId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(configIdList)) {
            appMapper.insertAppConfigRelationshipByAppId(appId, configIdList);
        }
    }

    @GetMapping("/{id}")
    public AppVO getOne(@PathVariable Long id) {
        return appMapper.findAppList(null, id).get(0);
    }
}
