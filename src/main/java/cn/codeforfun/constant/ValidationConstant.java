package cn.codeforfun.constant;

/**
 * 业务常量类
 *
 * @author wangbin
 */
public final class ValidationConstant {
    public static final String REGEX_CODE = "^[a-z]+[a-zA-Z0-9-]*$";
    public static final String ERROR_MESSAGE_CODE = "只能是数字、大小写字母和\"-\"，且第一个字符必须是英文";
    public static final String ERROR_MESSAGE_PROJECT_ID_NULL = "没有找到项目，请先选择一个项目";
    public static final String ERROR_MESSAGE_ENV_ID_NULL = "没有找到环境，请先选择一个项目";
}
