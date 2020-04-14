package com.my.lfy.exception;

/**
 * 基本异常信息定义
 *
 * @author xs
 */
public enum BaseExceptionMsg implements IExceptionMsg {

    /**
     *
     */
    EXECUTE_OK(0, "执行成功"),
    QUERY_OK(0, "查询成功"),
    EXECUTE_FAILD(-1, "执行失败"),
    FALLBACK_FAILD(-1, "熔断错误，执行失败"),
    ;


    private Integer code;
    private String message;

    BaseExceptionMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
