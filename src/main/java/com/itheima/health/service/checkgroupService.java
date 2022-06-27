package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface checkgroupService {

    PageResult pageQuery(QueryPageBean queryPageBean);

    void add(CheckGroup checkGroup);

    CheckGroup findByName(String name);

    void InsertIntoRelationalTable(Integer checkitemIds ,Integer checkgroupid);

    CheckGroup findbyId(@Param("id") Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
}
