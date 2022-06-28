package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

public interface SetmealService {
    void add(Setmeal setmeal, String checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);
}
