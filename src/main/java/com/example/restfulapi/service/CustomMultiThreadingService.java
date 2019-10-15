package com.example.restfulapi.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 异步调用线程池，写在这里，示例
 * Created by Administrator on 2019/10/8 0008.
 */
@Component
@Service
public class CustomMultiThreadingService {

    @Async("taskExecutor")
    public void tesTask(int i){
        System.out.println(Thread.currentThread().getName()+"-----"+i);
    }

    @Async("taskExecutor")
    public void stringTask(String str){

        System.out.println(Thread.currentThread().getName()+str);
    }
}
