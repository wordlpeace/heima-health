package com.itheima.health.dao;

import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Setmealdao {

    @Insert("insert into itcast_health.t_setmeal (t_setmeal.name, t_setmeal.code, t_setmeal.helpCode, t_setmeal.sex, t_setmeal.age, t_setmeal.price, t_setmeal.rem" +
            "ark, t_setmeal.attention, t_setmeal.img) values (#{name},#{code},#{helpCode},#{sex},#{age},#{price} ,#{remark},#{acction},#{img} )")
    void insert(Setmeal setmeal);
}
