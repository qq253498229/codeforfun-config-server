package cn.codeforfun.modules.app.vo;

import cn.codeforfun.generator.model.App;
import cn.codeforfun.modules.config.vo.ConfigVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppVO extends App {
    private List<ConfigVO> configList;
}
