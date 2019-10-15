package com.example.restfulapi.service;


import com.example.restfulapi.bean.ge.Admin;

import java.util.List;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
public interface AdminService {

    /**
     * 新增用户
     * @param admin
     * @return
     */
    boolean add(Admin admin);

    /**
     * 修改用户
     * @param admin
     * @return
     */
    boolean edit(Admin admin);

    /**
     * 重置密码
     * @param id
     * @param password
     * @return
     */
    boolean resetPwd(int id, String password);

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
    Admin findById(int id);

    /**
     * 根据username查询用户信息
     * @param username
     * @return
     */
    List<Admin> findByUsername(String username);

    /**
     * 更新登陆时间
     * @param username
     * @return
     */
    void updateLoginTime(String username);

    /**
     * 查询所有
     * @return
     */
    List<Admin> findAll(int page, int size, String name);

    /**
     * 查询总数
     * @return
     */
    int getTotal(String name);
}
