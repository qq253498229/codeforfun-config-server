package cn.codeforfun.modules.app.vo;

import cn.codeforfun.generator.model.App;
import cn.codeforfun.modules.config.vo.ConfigVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_PROJECT_ID_NULL;
import static cn.codeforfun.constant.ValidationConstant.REGEX_CODE;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppVO extends App {
    /**
     * 应用名
     */
    @Column(name = "app_name")
    @NotBlank(message = "\"应用名\" 不能为空")
    private String appName;

    /**
     * 应用编码
     */
    @Column(name = "app_code")
    @Pattern(regexp = REGEX_CODE, message = "\"应用编号\" 只能是 数字 、 字母和 \"-\"")
    @NotBlank(message = "\"应用编号\" 不能为空")
    private String appCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "project_id")
    @NotNull(message = ERROR_MESSAGE_PROJECT_ID_NULL)
    private Long projectId;

    private List<ConfigVO> configList;
}
