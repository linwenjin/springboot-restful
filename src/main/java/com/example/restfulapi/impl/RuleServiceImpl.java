package com.example.restfulapi.impl;

import com.example.restfulapi.bean.Rule;
import com.example.restfulapi.bean.ResponseCodeEnum;
import com.example.restfulapi.dao.RuleDao;
import com.example.restfulapi.middleware.BaseException;
import com.example.restfulapi.service.RuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现类
 * Created by Administrator on 2019/8/14 0014.
 */
@Service
public class RuleServiceImpl implements RuleService {
    @Resource
    private RuleDao thisDao;

    @Override
    public boolean add(Rule thisBean) {
        boolean flag = false;
        try{

            thisDao.add(thisBean);
            flag=true;
        }catch(Exception e){
            throw BaseException.out(ResponseCodeEnum.SAVE_FAIL, e);
        }

        return flag;
    }

    @Override
    public boolean edit(Rule thisBean) {
        boolean flag = false;
        try{
            thisDao.edit(thisBean);
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
    public Rule findById(int id) {
        return thisDao.findById(id);
    }

    @Override
    public Map findAll() {
        List<Rule> rules = thisDao.findAll();
        List fatherRuleList = new ArrayList();


        // 整理菜单
        for (int i = 0; i < rules.size(); i++) {
            Rule r = rules.get(i);
            if(r.getPid() == 0) {
                List temp = new ArrayList();
                for(Rule rr : rules){
                    if(rr.getPid() == r.getId()) {
                        Map childRule = new HashMap() {
                            {
                                put("id", rr.getId());
                                put("path", rr.getPath());
                                put("pid", rr.getPid());
                                put("icon", rr.getIcon());
                                put("component", rr.getComponent());
                                put("name", rr.getName());
                                put("is_leaf", rr.getIs_leaf());
                                put("is_show", rr.getIs_show());
                                put("order", rr.getOrder());
                                put("state", rr.getState());
                            }
                        };
                        temp.add(childRule);
                    }
                }
                Map fatherRule = new HashMap() {
                    {
                        put("id", r.getId());
                        put("path", r.getPath());
                        put("pid", r.getPid());
                        put("component", r.getComponent());
                        put("name", r.getName());
                        put("icon", r.getIcon());
                        put("is_leaf", r.getIs_leaf());
                        put("is_show", r.getIs_show());
                        put("order", r.getOrder());
                        put("state", r.getState());
                        put("children", temp);
                    }
                };
                fatherRuleList.add(fatherRule);
            }
        }

        Map result = new HashMap() {
            {
                put("ruleList", fatherRuleList);
            }
        };
        return result;
    }

}
