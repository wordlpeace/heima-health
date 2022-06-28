package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Setmealdao {

    @Insert("insert into itcast_health.t_setmeal (t_setmeal.name, t_setmeal.code, t_setmeal.helpCode, t_setmeal.sex, t_setmeal.age, t_setmeal.price, t_setmeal.rem" +
            "ark, t_setmeal.attention, t_setmeal.img) values (#{name},#{code},#{helpCode},#{sex},#{age},#{price} ,#{remark},#{attention},#{img} )")
    void insert(Setmeal setmeal);

    @Insert("insert into     itcast_health.t_setmeal_checkgroup(t_setmeal_checkgroup.setmeal_id, t_setmeal_checkgroup.checkgroup_id)" +
            "values (#{setmealid},#{groupid})")
    void addrelationtable(@Param("setmealid") Integer setmealid, @Param("groupid") Integer groupid);
 @Select("select * from itcast_health.t_setmeal where t_setmeal.name=#{name} ")
    Setmeal findbyname(String name);

    Page<Setmeal> selectByConditon(@Param("queryString") String queryString);
}
