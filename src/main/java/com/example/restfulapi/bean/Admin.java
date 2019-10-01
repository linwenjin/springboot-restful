package com.example.restfulapi.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
@Data
public class Admin {
    @Id
    private int id;
    private int role_id;
    private String username;
    private String nickname;
    private String password;
    private String salt;
    private String avatar;
    private String email;
    private String login_time;
    private String create_time;
    private String update_time;
    private String token;
    private int state;
}
