package com.example.restfulapi.controller.system;

import com.example.restfulapi.bean.ge.Admin;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.AdminService;
import com.example.restfulapi.until.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/18 0018.
 */
@RestController
@RequestMapping("/system")
public class LoginController {

    @Autowired
    private AdminService adminService;
    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Map login(@RequestParam String username, @RequestParam String password)
    {
        // 获取用户信息
        List<Admin> userInfoList = adminService.findByUsername(username);
        if(userInfoList.size() == 0 || userInfoList == null) {
            throw BaseException.out(ResponseCodeEnum.PASSWORD_FORBID,null);
        }
        Admin userInfo = userInfoList.get(0);
        // 验证账号密码
        if(!userInfo.getPassword().equals(DigestUtils.md5DigestAsHex((password+"/"+userInfo.getSalt()).getBytes()))) {
            throw BaseException.out(ResponseCodeEnum.PASSWORD_FORBID,null);
        }
        // 用户验证正确，则更新用户登录时间
        adminService.updateLoginTime(username);

        Map<String, ?> user = new HashMap() {
            {
                put("role_id", (userInfo.getRoleId()));
                put("username", (userInfo.getUsername()));
                put("nickname", (userInfo.getNickname()));
                put("avatar", (userInfo.getAvatar()));
            }
        };
        Map<String, ?> res = new HashMap() {
            {
                put("user", user);
                put("token", Jwt.getJwtToken(username));
            }
        };

        return res;
    }

    /**
     * 根据username和password
     * @return
     */
    @PostMapping("/login")
    public boolean login1()
    {

        return true;
    }
}
