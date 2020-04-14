package com.my.lfy.exception;

/**
 * 业务逻辑异常的封装
 *
 * @author cyj
 * @date 19-03-13
 */
public class ServiceException extends BaseException {

    public ServiceException(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public ServiceException(IExceptionMsg exception) {
        super(exception);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }


}
