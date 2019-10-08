package com.example.restfulapi.service.impl;

import com.example.restfulapi.bean.Admin;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.controller.common.CommonController;
import com.example.restfulapi.dao.AdminDao;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 实现类
 * Created by Administrator on 2019/8/14 0014.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao thisDao;

    @Override
    public boolean add(Admin thisBean) {
        boolean flag = false;
        try{
            // 盐
            String salt = CommonController.getRandomString(5);
            // 密码
            String pwd = thisBean.getPassword();
            thisBean.setSalt(salt);
            // 密码加密 <md5(明文密码 + / + 盐)>
            thisBean.setPassword(DigestUtils.md5DigestAsHex((pwd+"/"+salt).getBytes()));
            // 日期
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateFormat = formatter.format(date);
            thisBean.setLogin_time(dateFormat);
            thisBean.setCreate_time(dateFormat);
            thisBean.setUpdate_time(dateFormat);
            // token token_<md5(时间戳 + 盐)>
            thisBean.setToken("token_"+DigestUtils.md5DigestAsHex((System.currentTimeMillis()+"/"+salt).getBytes()));

            thisDao.add(thisBean);
            flag=true;
        }catch(Exception e){
            throw BaseException.out(ResponseCodeEnum.SAVE_FAIL, e);
        }

        return flag;
    }

    @Override
    public boolean edit(Admin thisBean) {
        boolean flag = false;
        try{
            // 日期
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateFormat = formatter.format(date);
            thisBean.setUpdate_time(dateFormat);

            thisDao.edit(thisBean);
            flag=true;
        }catch(Exception e){
            throw BaseException.out(ResponseCodeEnum.SAVE_FAIL, e);
        }

        return flag;
    }

    @Override
    public boolean resetPwd(int id, String password) {
        boolean flag = false;
        try{
            // 盐
            String salt = CommonController.getRandomString(5);
            // 密码加密 <md5(明文密码 + / + 盐)>
            String pwd = DigestUtils.md5DigestAsHex((password+"/"+salt).getBytes());
            thisDao.resetPwd(id, pwd, salt);
            flag=true;
        }catch(Exception e){
            throw BaseException.out(ResponseCodeEnum.SAVE_FAIL, e);
        }

        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag=false;
        try{
            thisDao.delete(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Admin findById(int id) {
        return thisDao.findById(id);
    }

    @Override
    public Admin findByUsername(String username) {
        return thisDao.findByUsername(username);
    }

    @Override
    public void updateLoginTime(String username) {
        String loginTime = CommonController.getStrTimeFormat("");
        thisDao.updateLoginTime(username, loginTime);
    }

    @Override
    public List<Admin> findAll(int page, String name) {
        return thisDao.findAll((page-1)*20, name);
    }

    @Override
    public int getTotal(String name) {
        return thisDao.getTotal(name);
    }
}
