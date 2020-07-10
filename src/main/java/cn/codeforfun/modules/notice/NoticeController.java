package cn.codeforfun.modules.notice;

import cn.codeforfun.base.BaseController;
import cn.codeforfun.modules.notice.exception.NoticeNotSupportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.monitor.PropertyPathEndpoint;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    private final PropertyPathEndpoint propertyPathEndpoint;

    public NoticeController(@Autowired(required = false) PropertyPathEndpoint propertyPathEndpoint) {
        this.propertyPathEndpoint = propertyPathEndpoint;
    }

    @GetMapping("/loadAll")
    public Map<String, Object> loadAll() {
        Map<String, Object> result = new HashMap<>(3);
        result.put("env", envMapper.selectAll());
        result.put("config", configMapper.selectAll());
        result.put("app", appMapper.selectAll());
        return result;
    }

    @PostMapping
    public void notice(@RequestBody Map<String, List<Integer>> param) {
        if (propertyPathEndpoint == null) {
            throw new NoticeNotSupportException();
        }
        List<Integer> envIdList = param.get("env");
        List<Integer> configIdList = param.get("config");
        List<Integer> appIdList = param.get("app");
        List<String> appCodeList = appMapper.findAppCodeListByEnvIdsOrConfigIdsOrAppIds(envIdList, configIdList, appIdList);
        Map<String, Object> map = new HashMap<>(1);
        map.put("path", appCodeList);
        propertyPathEndpoint.notifyByPath(new HttpHeaders(), map);
    }

}
