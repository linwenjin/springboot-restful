package com.example.restfulapi.middleware;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器,静态资源映射
 * Created by Administrator on 2019/8/25 0025.
 */
@Component
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 获取图片路径
     */
    @Value("${pre-read.uploadPath}")
    private String uploadPath;
    /**
     * 忽略路径
     */
    @Value("${pre-read.ignorePath}")
    private String ignorePath;
    /**
     * 跨域路径
     */
    @Value("${pre-read.allowOrigin}")
    private String allowOrigin;

    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 文件磁盘图片url 映射
        // 配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
//        registry.addResourceHandler("/upload/**").addResourceLocations("/","classpath:/static/upload/");
        registry.addResourceHandler("/upload/**").addResourceLocations("/","file:/"+uploadPath);

    }

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(ignorePath, allowOrigin));
    }
}
