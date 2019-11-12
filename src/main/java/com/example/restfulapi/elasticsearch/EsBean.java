package com.example.restfulapi.elasticsearch;

/**
 * Created by Administrator on 2019/10/22 0022.
 */
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class EsBean {
    private String k;

    private String v;

    public JSONObject getJson()
    {
        JSONObject object = new JSONObject();

        object.put("k", k);
        object.put("v", v);

        return object;

    }
}
