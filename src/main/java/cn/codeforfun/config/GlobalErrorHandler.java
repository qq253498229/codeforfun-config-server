package cn.codeforfun.config;

import lombok.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author wangbin
 */
@ControllerAdvice
public class GlobalErrorHandler {

    /**
     * 将controller中校验返回值转换成hibernate-validator相似的格式
     *
     * @param e       异常
     * @param request 请求
     * @return 影响体
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, ServletWebRequest request) {
        final Map<String, Object> result = getStringObjectMap(request, "参数错误");
        List<SimpleObjectError> errors = e.getBindingResult().getAllErrors().stream()
                .map(r -> new SimpleObjectError(r.getDefaultMessage(), r.getObjectName(), ((FieldError) r).getField(), ((FieldError) r).getRejectedValue(), r.getCode()))
                .collect(Collectors.toList());
        result.put("errors", errors);
        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public Map<String, Object> handleDuplicateKeyException(DuplicateKeyException e, ServletWebRequest request) {
        final Map<String, Object> result = getStringObjectMap(request, "参数错误");
        Map<String, Object> error = new HashMap<>();
        SQLIntegrityConstraintViolationException cause = (SQLIntegrityConstraintViolationException) e.getCause();
        String localizedMessage = cause.getLocalizedMessage();
        if (localizedMessage.contains("system_project.system_project_code_uindex")) {
            error.put("defaultMessage", "项目编号已存在");
        }
        result.put("errors", Collections.singletonList(error));
        return result;
    }

    private Map<String, Object> getStringObjectMap(ServletWebRequest request, String message) {
        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("path", request.getRequest().getRequestURI());
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.put("message", message);
        return result;
    }

    @Value
    static class SimpleObjectError {
        String defaultMessage;
        String objectName;
        String field;
        Object rejectedValue;
        String code;
    }
}
