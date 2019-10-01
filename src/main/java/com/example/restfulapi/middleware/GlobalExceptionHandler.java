package com.example.restfulapi.middleware;

import com.example.restfulapi.bean.RestResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/8/20 0020.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
//    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Integer errorCode;
    private Integer httpCode;
    private String errorMsg;
    /**
     * debug打开，会显示具体错误内容，上线时关闭
     */
    @Value("${pre-read.debug}")
    private boolean debug;


    /**
     * 系统异常
     * 捕捉到404，业务异常<baseException>，其他<500>
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        logger.error("", e);
        Object thisData = null;
        errorMsg = e.toString();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            errorCode = 10001;
            httpCode = 404;
        } else if(e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            httpCode = 200;
            errorCode = baseException.getCode();
            errorMsg = baseException.getErrorMsg();
            thisData = this.debug ? baseException.getDebugE() : null;
        }else {
            errorCode = 10001;
            httpCode = 500;

        }

        RestResult res = new RestResult(this.httpCode,this.errorCode, thisData, errorMsg);
        return res;
    }
}
