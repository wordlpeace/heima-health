package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface checkgroupdao
{
    Page<CheckGroup> selectByConditon(@Param("queryString") String queryString);

    void insert(CheckGroup checkGroup);

    CheckGroup findByName(@Param("name") String name);

    void InsertIntoRelationalTable(@Param("checkgroupid") Integer checkgroupid, @Param("checkitemid") Integer checkitemid);

    CheckGroup findById(@Param("id") Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id") Integer id);
}
