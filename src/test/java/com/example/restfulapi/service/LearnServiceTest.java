package com.example.restfulapi.service;

import com.example.restfulapi.service.impl.AdminServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2019/10/8 0008.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnServiceTest {
    @Autowired
    private AdminServiceImpl impl;

    @Test
    public void getLearn(){
        Assert.assertEquals(impl.getTotal(""),1);
    }
}
