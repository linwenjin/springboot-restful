package com.example.restfulapi.bean;

/**
 * 错误code
 * Created by Administrator on 2019/8/20 0020.
 */
public enum ResponseCodeEnum {
    // 系统通用
    ILLEGAL_LOGIN(1010, "登录超时"),
    PASSWORD_FORBID(1011, "账号或密码错误"),
    DO_NOT_EDIT(1012, "展示版不可修改"),

    OPERATE_FAIL(666, "操作失败"),

    SAVE_FAIL(1001, "保存失败"),

    // Admin

    ADMIN_USER_EXIST(2001, "重复的用户名"),

    GET_USER_INFO_FAILED(2002, "保存用户信息失败"),

    WECHAT_VALID_FAILED(2003, "微信验证失败"),

    GET_USER_AUTH_INFO_FAILED(2004, "根据条件获取用户授权信息失败"),

    SAVE_USER_AUTH_INFO_FAILED(2005, "保存用户授权失败");

    private Integer errorCode;
    private String errorMsg;

    ResponseCodeEnum(Integer code, String message) {
        this.errorCode = code;
        this.errorMsg = message;
    }

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final String getErrorMsg() {
        return this.errorMsg;
    }
}
