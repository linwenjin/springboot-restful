package com.example.restfulapi.middleware;

import com.example.restfulapi.bean.ResponseCodeEnum;

/**
 * 业务逻辑异常
 * 调用方式：throw BaseException.out(ResponseCodeEnum.FAIL, e);
 *
 * Created by Administrator on 2019/8/20 0020.
 */
public class BaseException extends RuntimeException {

    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 额外的报错
     */
    private Object debugE;
    /**
     * 错误码
     */
    private Integer code;


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getDebugE() {
        return debugE;
    }

    public void setDebugE(Object debugE) {
        this.debugE = debugE;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    private BaseException(ResponseCodeEnum responseCodeEnum, Object e) {
        super(responseCodeEnum.getErrorMsg());
        this.code = responseCodeEnum.getErrorCode();
        this.errorMsg = responseCodeEnum.getErrorMsg();
        this.debugE = e;
    }

    /**
     * 抛出逻辑异常
     *
     * @param responseCodeEnum
     * @return
     */
    public static BaseException out(ResponseCodeEnum responseCodeEnum, Object e) {
        return new BaseException(responseCodeEnum, e);
    }
}
