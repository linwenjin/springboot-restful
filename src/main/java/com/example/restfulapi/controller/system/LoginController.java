package com.example.restfulapi.controller.system;

import com.example.restfulapi.bean.Admin;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.controller.common.JwtController;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/18 0018.
 */
@RestController
@RequestMapping("/system")
//@CrossOrigin(origins = "http://localhost:8080")
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
        Admin userInfo = adminService.findByUsername(username);
        // 验证账号密码
        if(userInfo == null || !userInfo.getPassword().equals(DigestUtils.md5DigestAsHex((password+"/"+userInfo.getSalt()).getBytes()))) {
            throw BaseException.out(ResponseCodeEnum.PASSWORD_FORBID,null);
        }
        // 用户验证正确，则更新用户登录时间
        adminService.updateLoginTime(username);

        Map<String, ?> user = new HashMap() {
            {
                put("role_id", (userInfo.getRole_id()));
                put("username", (userInfo.getUsername()));
                put("nickname", (userInfo.getNickname()));
                put("avatar", (userInfo.getAvatar()));
            }
        };
        Map<String, ?> res = new HashMap() {
            {
                put("user", user);
                put("token", JwtController.getJwtToken(username));
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
