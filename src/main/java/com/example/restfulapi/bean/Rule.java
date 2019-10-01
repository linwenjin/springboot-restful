package com.example.restfulapi.bean;

import lombok.Data;

import javax.persistence.Id;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
@Data
public class Rule {
    @Id
    private int id;
    private int pid;
    private String path;
    private String name;
    private String component;
    private String icon;
    private int order;
    private String is_show;
    private String is_leaf;
    private int state;
}
