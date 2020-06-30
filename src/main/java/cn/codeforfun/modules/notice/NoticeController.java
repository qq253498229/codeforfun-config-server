package cn.codeforfun.modules.notice;

import cn.codeforfun.base.BaseController;
import cn.codeforfun.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.monitor.PropertyPathEndpoint;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.codeforfun.constant.BusinessConstant.CONTEXT_PATH;

/**
 * @author wangbin
 */
@RestController
@RequestMapping(CONTEXT_PATH + "/notice")
@Validated
public class NoticeController extends BaseController {
    @Autowired(required = false)
    private PropertyPathEndpoint propertyPathEndpoint;

    @PostMapping("/refresh")
    public void refresh(@RequestHeader HttpHeaders headers, @RequestBody Map<String, Object> request) {
//        propertyPathEndpoint.notifyByPath(headers, request);
        System.out.println(1);
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
    public void test(@RequestBody Map<String, List<Map<String, Integer>>> param) throws JsonProcessingException {
        System.out.println(JsonUtils.toPrettyJson(param));
        List<Map<String, Integer>> envIdList = param.get("env");
        List<Map<String, Integer>> configIdList = param.get("config");
        List<Map<String, Integer>> appIdList = param.get("app");
        System.out.println(1);

    }

}
