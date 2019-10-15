package com.example.restfulapi.middleware;

import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.controller.common.CommonController;
import com.example.restfulapi.controller.common.JwtController;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器,验证JWT
 * Created by Administrator on 2019/9/5 0005.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    // 过滤路径（除GET外的）
    private String ignorePath;
    // 允许跨域的路径，用英文逗号隔开
    private String allowOrigin;

    /**
     * 构造函数
     * @param ignorePath
     */
    public AuthenticationInterceptor(String ignorePath, String allowOrigin) {
        this.ignorePath = ignorePath;
        this.allowOrigin = allowOrigin;
    }

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if(request.getServletPath().equals("/system/login")) {
            // 记录来访者ip
            String ip = CommonController.getIp(request);
//            CommonController.fileWrite("E:/upload/ip.txt", ip);
            CommonController.fileWrite("/www/wwwroot/java/upload/ip.txt", ip);
        }


        // 解决preHandle下的跨域问题
        String[] whiteOrigin = allowOrigin.split(",");
        for (String thisOrigin:whiteOrigin) {
            if(thisOrigin.equals(request.getHeader("Origin"))) {
                response.addHeader("Access-Control-Allow-Origin", thisOrigin);
                break;
            }
        }
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,token");
        response.addHeader("Access-Control-Max-Age", "3600");

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        // 过滤GET，GET一般都是做获取信息，此处省略
        if("GET".equals(request.getMethod())) {
            return true;
        }

        // 获取除域名外的完整方法
        StringBuffer path = request.getRequestURL();
        String pathStr = new String(path);
        String action = "/" + pathStr.split("/", 4)[3];

        // 过滤不需要判断的路径
        if(ignorePath != null) {
            String[] ignorePathTmp = ignorePath.split(",");
            for (int i = 0; i < ignorePathTmp.length; i++) {
                if(ignorePathTmp[i] == action) {
                    return true;
                }
            }
        }

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        if(token == null || token == "") {
            throw BaseException.out(ResponseCodeEnum.ILLEGAL_LOGIN, "token is not found");
        }
        // 获取username
        String username = JwtController.JwtTokenVerify(token);
        // 权限判断


        return true;
    }
    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
