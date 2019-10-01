package com.example.restfulapi.controller.system;

import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.bean.Rule;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class RuleController {

    @Autowired
    private RuleService thisService;
    private final String action = "/rule";


    @PostMapping(action)
    public boolean add(Rule thisBean) {
        System.out.println("开始新增...");
        return thisService.add(thisBean);
    }

    @PutMapping(action+"/id/{id}")
    public boolean edit(@PathVariable int id, Rule thisBean) {
        throw BaseException.out(ResponseCodeEnum.DO_NOT_EDIT, null);

//        System.out.println("开始更新...");
//        return thisService.edit(thisBean);
    }

    @DeleteMapping(action+"/id/{id}")
    public boolean delete(@PathVariable int id) {
        throw BaseException.out(ResponseCodeEnum.DO_NOT_EDIT, null);

//        System.out.println("开始删除...");
//        return thisService.delete(id);
    }

    @GetMapping(action+"/id/{id}")
    public Rule findById(@PathVariable int id) {
        System.out.println("开始查询...");
        return thisService.findById(id);
    }
    
    @GetMapping(action)
    public Map getAll() {
        System.out.println("开始查询响应数据...");

        return thisService.findAll();
    }

}
