package com.example.restfulapi.controller.common;

import com.example.restfulapi.middleware.CustomMultiThreadingConfig;
import com.example.restfulapi.service.CustomMultiThreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2019/10/8 0008.
 */
@RestController
@RequestMapping(value = "/multithreading")
public class CustomMultiThreadingController {
    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    @ResponseBody
    @RequestMapping(value="/dotask")
    public Object doTask() {
        for (int i=0;i<100;i++){
            customMultiThreadingService.tesTask(i);
            customMultiThreadingService.stringTask("abc");
        }

        return 1;
    }

//    @RequestMapping(value="/test")
//    public Map test() {
//
//        CustomMultiThreadingConfig e = new CustomMultiThreadingConfig();
//        return e.getThreadingInfo();
//    }
}
