package com.my.lfy.utils;

import com.my.lfy.exception.BaseExceptionMsg;
import com.my.lfy.exception.IExceptionMsg;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * JsonResult
 *
 * @author lfy
 * @date 19-4-28
 **/
@Slf4j
public class JsonResult<T> implements Serializable {
    /**
     * 数据
     */
    private T data;
    /**
     * 信息
     */
    private String message;

    /**
     * 接口是否成功执行
     */
    private boolean success;

    /**
     * 错误码
     */
    private int code;

    /**
     * 构造函数
     */
    public JsonResult() {
        this.data = null;
        this.message = BaseExceptionMsg.EXECUTE_OK.getMessage();
        this.code = BaseExceptionMsg.EXECUTE_OK.getCode();
        this.success = (this.code == 0) ? true : false;
    }

    public JsonResult(T data) {
        this.data = data;
        this.message = BaseExceptionMsg.EXECUTE_OK.getMessage();
        this.code = BaseExceptionMsg.EXECUTE_OK.getCode();
        this.success = (this.code == 0) ? true : false;
    }

    public JsonResult(IExceptionMsg msg) {
        this.data = null;
        this.message = msg.getMessage();
        this.code = msg.getCode();
        this.success = (this.code == 0) ? true : false;
    }

    public JsonResult(int code, String message) {
        this.data = null;
        this.message = message;
        this.code = code;
        this.success = (this.code == 0) ? true : false;
    }

    public JsonResult(int code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.success = (this.code == 0) ? true : false;
    }

    public JsonResult(IExceptionMsg message, T data) {
        this.data = data;
        this.message = message.getMessage();
        this.code = message.getCode();
        this.success = (message.getCode() == 0) ? true : false;
    }

    public JsonResult(IExceptionMsg msg, String message) {
        this(msg);
        this.message = message;
    }

    public JsonResult(IExceptionMsg msg, String message, T data) {
        this(msg);
        this.message = message;
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}