package cn.codeforfun.modules.env.vo;

import cn.codeforfun.generator.model.Env;
import cn.codeforfun.modules.config.vo.ConfigVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static cn.codeforfun.constant.ValidationConstant.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnvVO extends Env {
    /**
     * 环境名
     */
    @Column(name = "env_name")
    @NotBlank(message = "\"环境名\" 不能为空")
    private String envName;

    /**
     * 环境编号
     */
    @Column(name = "env_code")
    @Pattern(regexp = REGEX_CODE, message = "\"环境编号\"" + ERROR_MESSAGE_CODE)
    @NotBlank(message = "\"环境编号\" 不能为空")
    private String envCode;

    /**
     * 项目id(外键)
     */
    @Column(name = "project_id")
    @NotNull(message = ERROR_MESSAGE_PROJECT_ID_NULL)
    private Long projectId;

    private List<ConfigVO> configList;
}
