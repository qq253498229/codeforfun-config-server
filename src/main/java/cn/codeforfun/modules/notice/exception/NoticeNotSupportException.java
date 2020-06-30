package cn.codeforfun.modules.notice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static cn.codeforfun.constant.ValidationConstant.ERROR_MESSAGE_NOTICE_NOT_SUPPORT;

/**
 * @author wangbin
 */
@ResponseStatus(HttpStatus.FOUND)
public class NoticeNotSupportException extends RuntimeException {
    public NoticeNotSupportException() {
        super(ERROR_MESSAGE_NOTICE_NOT_SUPPORT);
    }
}
