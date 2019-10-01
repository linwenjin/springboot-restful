package com.example.restfulapi.dao;

import com.example.restfulapi.bean.Rule;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2019/8/14 0014.
 */
@Mapper
public interface RuleDao {

    String table = "lin_rule";

    /**
     * 用户数据新增
     */
    @Insert({
            "INSERT INTO " + table,
            "(`pid`,`path`,`name`,`component`,`icon`,`order`,`is_show`,`is_leaf`,`state`)",
            "VALUES",
            "(#{pid},#{path},#{name},#{component},#{icon},#{order},#{is_show},#{is_leaf},#{state})"
    })
    void add(Rule rule);

    /**
     * 用户数据修改
     */
    @Update({"UPDATE "+ table,
            "SET `pid`=#{pid},",
            "`path`=#{path},",
            "`name`=#{name},",
            "`component`=#{component},",
            "`icon`=#{icon},",
            "`order`=#{order},",
            "`is_show`=#{is_show},",
            "`is_leaf`=#{is_leaf},",
            "`state`=#{state}",
            "WHERE `id`=#{id}"})
    void edit(Rule rule);

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
    Rule findById(@Param("id") int id);

    /**
     * 查询所有
     */
    @Select({"SELECT",
            "*",
            "FROM " + table,
            "ORDER BY `order` ASC"})
    List<Rule> findAll();

}
