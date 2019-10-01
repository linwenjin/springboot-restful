package com.example.restfulapi.dao;

import com.example.restfulapi.bean.Admin;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
@Mapper
public interface AdminDao {

    String table = "lin_admin";

    /**
     * 用户数据新增
     */
    @Insert({
            "INSERT INTO " + table,
            "(`username`,`nickname`,`role_id`,`password`,`salt`,`avatar`,`email`,`login_time`,`create_time`,`update_time`,`token`,`state`)",
            "VALUES",
            "(#{username},#{nickname},#{role_id},#{password},#{salt},#{avatar},#{email},#{login_time},#{create_time},#{update_time},#{token},#{state})"
    })
    void add(Admin admin);

    /**
     * 用户数据修改
     */
    @Update({"UPDATE "+ table,
            "SET username=#{username},",
            "role_id=#{role_id},",
            "nickname=#{nickname},",
            "avatar=#{avatar},",
            "email=#{email},",
            "update_time=#{update_time},",
            "state=#{state}",
            "WHERE id=#{id}"})
    void edit(Admin admin);

    /**
     * 重置密码
     */
    @Update({"UPDATE "+ table,
            "SET password=#{password},",
            "salt=#{salt}",
            "WHERE id=#{id}"})
    void resetPwd(@Param("id") int id, @Param("password") String password, @Param("salt") String salt);

    /**
     * 用户数据删除
     */
    @Delete("delete from "+ table +" where id=#{id}")
    void delete(int id);

    /**
     * 根据id查询用户信息
     *
     */
    @Select("SELECT id,name,age FROM "+ table +" where id=#{id}")
    Admin findById(@Param("id") int id);

    /**
     * 根据username<唯一索引>查询用户信息
     *
     */
    @Select({"SELECT",
            "id, username, nickname, password, salt, role_id, avatar",
            "FROM "+ table,
            "WHERE username=#{username}",
            "AND state = 1"})
    Admin findByUsername(@Param("username") String username);
    /**
     * 更新登陆时间
     *
     */
    @Select({"UPDATE "+ table,
            "SET login_time = #{loginTime}",
            "WHERE username=#{username}"})
    void updateLoginTime(@Param("username") String username, @Param("loginTime") String loginTime);

    /**
     * 查询所有
     */
    @Select({"<script>",
            "SELECT",
            "id, username, nickname, role_id, avatar, email, login_time, create_time, update_time, state",
            "FROM " + table,
            "WHERE 1=1",
            "<when test='name!=null'>",
            "AND (nickname LIKE CONCAT('%',#{name},'%')) OR (username LIKE CONCAT('%',#{name},'%'))",
            "</when>",
            "LIMIT #{p}, 20",
            "</script>"})
    List<Admin> findAll(@Param("p") int p, @Param("name") String name);

    /**
     * 查询个数
     */
    @Select({"<script>",
            "SELECT count(1) FROM " + table,
            "WHERE 1=1",
            "<when test='name!=null'>",
            "AND nickname LIKE CONCAT('%',#{name},'%')",
            "</when>",
            "</script>"})
    int getTotal(@Param("name") String name);
}
