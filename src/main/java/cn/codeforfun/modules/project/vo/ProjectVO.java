package cn.codeforfun.modules.project.vo;

import cn.codeforfun.generator.model.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_CODE;
import static cn.codeforfun.constant.ValidationConstant.REGEX_CODE;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectVO extends Project {
    /**
     * 项目名
     */
    @Column(name = "project_name")
    @NotBlank(message = "\"项目名\" 不能为空")
    private String projectName;

    /**
     * 项目编号
     */
    @Column(name = "project_code")
    @Pattern(regexp = REGEX_CODE, message = "\"项目编号\"" + ERROR_MESSAGE_CODE)
    @NotBlank(message = "\"项目编号\" 不能为空")
    private String projectCode;
}
