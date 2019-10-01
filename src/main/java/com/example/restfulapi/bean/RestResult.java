package com.example.restfulapi.bean;

import lombok.Data;

/**
 * 统一返回接口格式
 * Created by Administrator on 2019/8/14 0014.
 */
@Data
public class RestResult {
    /**
     * http Status Code码
     * 200 响应正常
     */
    private Integer httpCode;
    /**
     * 成功或者失败的code错误码
     * 0 成功(无错误)
     */
    private Integer errorCode;
    /**
     * 成功时返回的数据，失败时返回具体的异常信息
     */
    private Object data;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    private String errorMsg;
    /**
     * 服务器当前时间（添加该字段的原因是便于查找定位请求时间，因为实际开发过程中服务器时间可能跟本地时间不一致，加上这个时间戳便于日后定位）
     */
    private long time;

    public RestResult(Integer httpCode, Integer errorCode, Object data, String errorMsg) {
        this.errorCode = errorCode;
        this.data = data;
        this.httpCode = httpCode;
        this.errorMsg = errorMsg;
        this.time = System.currentTimeMillis();
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
