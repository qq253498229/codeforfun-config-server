package cn.codeforfun.modules.app.vo;

import cn.codeforfun.generator.model.Application;
import cn.codeforfun.modules.config.vo.ConfigVO;
import lombok.Data;

import java.util.List;

@Data
public class AppVO {
    private Long applicationId;
    private Application app;
    private List<ConfigVO> configList;
}
