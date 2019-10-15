package com.example.restfulapi.service.impl;

import com.example.restfulapi.bean.ge.Admin;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.bean.ge.AdminExample;
import com.example.restfulapi.dao.AdminMapper;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.AdminService;
import com.example.restfulapi.until.Common;
import com.example.restfulapi.until.VeDate;
import com.github.pagehelper.PageHelper;
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
    private AdminMapper thisDao;

    @Override
    public boolean add(Admin thisBean) {
        boolean flag = false;
        try{
            // 盐
            String salt = Common.getRandomString(5);
            // 密码
            String pwd = thisBean.getPassword();
            thisBean.setSalt(salt);
            // 密码加密 <md5(明文密码 + / + 盐)>
            thisBean.setPassword(DigestUtils.md5DigestAsHex((pwd+"/"+salt).getBytes()));
            // 日期
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
//            String dateFormat = formatter.format(date);
            thisBean.setLoginTime(date);
            thisBean.setCreateTime(date);
            thisBean.setUpdateTime(date);
            // token token_<md5(时间戳 + 盐)>
            thisBean.setToken("token_"+DigestUtils.md5DigestAsHex((System.currentTimeMillis()+"/"+salt).getBytes()));

            thisDao.insertSelective(thisBean);
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
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
//            String dateFormat = formatter.format(date);
            thisBean.setUpdateTime(date);

            thisDao.updateByPrimaryKeySelective(thisBean);
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
            String salt = Common.getRandomString(5);
            // 密码加密 <md5(明文密码 + / + 盐)>
            String pwd = DigestUtils.md5DigestAsHex((password+"/"+salt).getBytes());

            Admin admin = new Admin();
            admin.setId(id);
            admin.setPassword(pwd);

            thisDao.updateByPrimaryKeySelective(admin);
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
            thisDao.deleteByPrimaryKey(id);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Admin findById(int id) {
        return thisDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Admin> findByUsername(String username) {

        AdminExample example = new AdminExample();
        example.or().andUsernameEqualTo(username);

        return thisDao.selectByExample(example);
    }

    @Override
    public void updateLoginTime(String username) {
        Admin admin = new Admin();
        admin.setUpdateTime(VeDate.getNowDate());

        AdminExample example = new AdminExample();
        example.or().andUsernameEqualTo(username);
        thisDao.updateByExampleSelective(admin, example);
    }

    @Override
    public List<Admin> findAll(int page, int size, String name) {
        // 分页
        PageHelper.startPage(page, size);

        AdminExample example = new AdminExample();
        example.or().andNicknameEqualTo(name);
        return thisDao.selectByExample(example);
    }

    @Override
    public int getTotal(String name) {
        AdminExample example = new AdminExample();
        example.or().andNicknameEqualTo(name);

        return thisDao.countByExample(example);
    }
}
