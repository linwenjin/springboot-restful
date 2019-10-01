package com.example.restfulapi.controller.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/8/17 0017.
 */
@RestController
@RequestMapping("/common")
@CrossOrigin(origins = "http://localhost:8080")
public class UploadController {


    /**
     * 获取图片路径
     */
    @Value("${pre-read.uploadPath}")
    private String uploadPath;

    /**
     * 项目路径
     */
    @Value("${server.servlet.context-path}")
    private String ctxPath;

    /**
     * 上传图片，可单张可多张，返回list为图片地址
     */
    @PostMapping("/uploadImg")
    public List uploadImg(@RequestParam("multipartfiles") MultipartFile[] multipartfiles, String productId, HttpServletRequest request) throws IOException {
        // 定义返回的list
        List imgList = new ArrayList();
        System.out.println(multipartfiles);


        // 访问路径
//        String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + ctxPath +"/upload/imgs/";
        String returnUrl = "http://img.linwenjin.top/upload/imgs/";
        // 实际文件路径，要在静态资源映射统一
        String path = uploadPath+"imgs"; //文件存储位置

        // 遍历文件
        if(multipartfiles != null && multipartfiles.length>0){
            for(MultipartFile item : multipartfiles){

                String fileName = item.getOriginalFilename();//获取文件名
                // 改名
                String fileF = fileName.substring(fileName.lastIndexOf("."));//文件后缀
                fileName = new Date().getTime()+"_"+new Random().nextInt(1000) + fileF;//新的文件名

                // 这里可以按日期分开
                String filePath = path;
                imgList.add(returnUrl + fileName);
                File file = new File(filePath, fileName);
                // 创建文件夹
                if(!file.exists()){
                    if(!file.getParentFile().exists()){
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                // 上传文件
                item.transferTo(file);
            }
        }

        return imgList;
    }
}
