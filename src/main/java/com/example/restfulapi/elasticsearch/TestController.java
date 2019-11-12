package com.example.restfulapi.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/22 0022.
 */
@RestController
@RequestMapping("/es")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${es.url}")
    private String url;

    /**
     * 获取所有索引
     * @return
     * @throws IOException
     */
    @GetMapping("/index")
    public Object getAllIndex() {
        String apiURL = url+"/_cat/indices?v";
        List res = str2map(restTemplate.getForObject(apiURL, String.class));

        return res;
    }

    /**
     * 创建索引，如存在则清空覆盖，_version +1
     * @param index
     * @return
     */
    @PostMapping("/index/{index}/{type}/{id}")
    public Object putIndex(@PathVariable String index, @PathVariable String type, @PathVariable String id)
    {
        JSONObject jsonObject = new JSONObject();
        Object res = restTemplate.postForObject(url+"/"+index+"/"+type+"/"+id, jsonObject, Object.class);

        return res;
    }

    /**
     * 删除索引
     * @param index
     * @return
     */
    @DeleteMapping("/index/{index}")
    public Object deleteIndex(@PathVariable String index)
    {
        try {
            restTemplate.delete(url+"/"+index);
        } catch (Exception e) {
            return e.getMessage();
        }

        return true;
    }

    /**
     * 查询索引结构及文档内容
     * @param index
     * @return
     */
    @GetMapping("/{index}/{type}/{id}")
    public Object getDoc(@PathVariable String index, @PathVariable String type, @PathVariable String id)
    {
        Object res = restTemplate.getForObject(url+"/"+index+"/"+type+"/"+id, Object.class);

        return res;
    }

    /**
     * 添加文档内容
     * @param index
     * @param type
     * @param id
     * @param esBean
     * @return
     */
    @PostMapping("/{index}/{type}/{id}")
    public Object postDoc(@PathVariable String index,
                                   @PathVariable String type,
                                   @PathVariable String id,
                                   EsBean esBean)
    {

        Object res = restTemplate.postForObject(url+"/"+index+"/"+type+"/"+id+"?pretty", esBean.getJson(), Object.class);

        return res;
    }

    /**
     * 删除文档
     * @param index
     * @param type
     * @param id
     * @return
     */
    @DeleteMapping("/{index}/{type}/{id}")
    public Object DeleteDoc(@PathVariable String index,
                         @PathVariable String type,
                         @PathVariable String id)
    {

        try {
            restTemplate.delete(url+"/"+index+"/"+type+"/"+id);
        } catch (Exception e) {
            return e.getMessage();
        }

        return true;
    }




    /**
     * 将结果的string转成map
     * @param str
     * @return
     */
    private List str2map(String str)
    {
        String[] tempArr = str.split("\n");
        String[] keyArr = deleteArrayNull(tempArr[0].split(" "));
        List res = new ArrayList();

        for(int i = 1; i<= tempArr.length-1; i++) {
            Map info = new HashMap();
            String[] valueArr = deleteArrayNull(tempArr[i].split(" "));
            for(int j = 0; j<= valueArr.length-1; j++) {
                info.put(keyArr[j], valueArr[j]);
            }
            res.add(info);
        }

        return res;
    }

    /**
     * 去除数组中的空值
     * @param string
     * @return
     */
    private String[] deleteArrayNull(String[] string) {
        String[] array = string;
        // 声明一个list
        List<String> list= new ArrayList<>(array.length);
        for (String str : array) {
            list.add(str.trim());
        }

        // 删除空的值
        while (list.remove(null));
        while (list.remove(""));

        // 将list 转换成数组
        String []list2 = list.toArray(new String[list.size()]);
        // 返回删除空值后的数组
        return list2;
    }

}

