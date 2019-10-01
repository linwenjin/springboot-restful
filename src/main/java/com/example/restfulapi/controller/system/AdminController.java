package com.example.restfulapi.controller.system;

import com.example.restfulapi.bean.Admin;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * GET /api/v1/<action> 所有书单
 * GET /api/v1/<action>/id/{id} 根据id获取一条书单
 * POST /api/v1/<action> 新建一条书单
 * PUT /api/v1/<action>/id/{id} 根据id更新一条
 * PATCH /api/v1/<action>/id/{id} 更新一条书单，提供部分信息
 * DELETE /api/v1/<action>/id/{id} 删除一条书单
 *
 * Created by Administrator on 2019/8/14 0014.
 */
@RestController
@RequestMapping(value = "/system")
public class AdminController {

    @Autowired
    private AdminService thisService;
    private final String action = "/admin";


    @PostMapping(action)
    public boolean add(Admin thisBean) {
        System.out.println("开始新增...");
        // 判断用户是否存在
        if(thisService.findByUsername(thisBean.getUsername()) != null) {
            throw BaseException.out(ResponseCodeEnum.ADMIN_USER_EXIST, null);
        }

        return thisService.add(thisBean);
    }

    @PutMapping(action+"/id/{id}")
    public boolean edit(@PathVariable int id, Admin thisBean) {
        throw BaseException.out(ResponseCodeEnum.DO_NOT_EDIT, null);

//        System.out.println("开始更新...");
//        // 判断用户是否存在,还需判断取到的id是否为传过来的id
//        int findUserId = thisService.findByUsername(thisBean.getUsername()).getId();
//        if(findUserId != thisBean.getId()) {
//            throw BaseException.out(ResponseCodeEnum.ADMIN_USER_EXIST, null);
//        }
//        return thisService.edit(thisBean);
    }

    @PutMapping(action+"/pwd/id/{id}")
    public boolean resetPwd(@PathVariable int id, @RequestParam String password) {
        throw BaseException.out(ResponseCodeEnum.DO_NOT_EDIT, null);

//        System.out.println("开始重置密码...");
//        return thisService.resetPwd(id, password);
    }

    @DeleteMapping(action+"/id/{id}")
    public boolean delete(@PathVariable int id) {
        throw BaseException.out(ResponseCodeEnum.DO_NOT_EDIT, null);

//        System.out.println("开始删除...");
//        return thisService.delete(id);
    }

    @GetMapping(action+"/id/{id}")
    public Admin findById(@PathVariable int id) {
        System.out.println("开始查询...");
        return thisService.findById(id);
    }

    @GetMapping("test")
    public void test(HttpServletRequest request) {
    }
    
    @GetMapping(action)
    public  Map<String, ?> getAll(@RequestParam int page, @RequestParam String name) {
        System.out.println("开始查询响应数据...");
        Map res = new HashMap();
        res.put("total", thisService.getTotal(name));
        res.put("list", thisService.findAll(page, name));

        return res;
    }

}
