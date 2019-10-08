package com.example.restfulapi.controller.common;

import com.example.restfulapi.middleware.CustomMultiThreadingConfig;
import com.example.restfulapi.service.CustomMultiThreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2019/10/8 0008.
 */
@RestController
@RequestMapping(value = "/multithreading")
public class CustomMultiThreadingController {
    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;
    @Autowired
    private CustomMultiThreadingConfig e;

    @ResponseBody
    @RequestMapping(value="/dotask")
    public Map doTask() {

        Map map = e.getThreadingInfo();
        return map;
    }
}
