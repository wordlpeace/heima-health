package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    void edit(CheckGroup checkGroup);

    void deleteBycheckGroup(@Param("id") Integer id);

    Integer countCheckItemIdByCheckGroup(@Param("id") Integer id);

    void delete(@Param("id") Integer id);

    void Deleterelationtable(@Param("id") Integer id);

    @Select("select * from t_checkgroup;")
    List<CheckGroup> findAll();
}
