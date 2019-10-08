package com.example.restfulapi.middleware;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义多线程配置（线程池）
 * Created by Administrator on 2019/10/8 0008.
 */
@Configuration
@ComponentScan()
@EnableAsync
public class CustomMultiThreadingConfig implements AsyncConfigurer {


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心池大小
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(25); // 队列程度
        executor.setKeepAliveSeconds(1000); // 线程空闲时间
//        executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
        /**
         * ThreadPoolExecutor
         * AbortPolicy，用于被拒绝任务的处理程序，它将抛出RejectedExecutionException。
         * CallerRunsPolicy，用于被拒绝任务的处理程序，它直接在execute方法的调用线程中运行被拒绝的任务。
         * DiscardOldestPolicy，用于被拒绝任务的处理程序，它放弃最旧的未处理请求，然后重试execute。
         * DiscardPolicy，用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
         */
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }

    @Autowired
    private Executor taskExecutor;
    public Map getThreadingInfo() {
        Map map =new HashMap();
        Object[] myThread = {taskExecutor};

        for (Object thread : myThread) {
            ThreadPoolTaskExecutor threadTask = (ThreadPoolTaskExecutor) thread;
            ThreadPoolExecutor threadPoolExecutor =threadTask.getThreadPoolExecutor();

            map.put("总线程1",threadPoolExecutor.getPoolSize());
            map.put("总线程2",threadPoolExecutor.getCorePoolSize());
            map.put("当前有多少线程正在处理任务",threadPoolExecutor.getActiveCount());
            map.put("还剩多少个任务未执行",threadPoolExecutor.getQueue().size());
            map.put("当前可用队列长度",threadPoolExecutor.getQueue().remainingCapacity());
        }

        return  map;
    }
}
