package com.example.restfulapi.service;

import com.example.restfulapi.bean.Rule;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
public interface RuleService {

    /**
     * 新增用户
     * @param rule
     * @return
     */
    boolean add(Rule rule);

    /**
     * 修改用户
     * @param rule
     * @return
     */
    boolean edit(Rule rule);


    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Rule findById(int id);


    /**
     * 查询所有
     * @return
     */
    Map findAll();

}
