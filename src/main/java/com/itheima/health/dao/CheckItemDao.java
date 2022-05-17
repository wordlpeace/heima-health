package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangmeng
 * @description j检查组dao层
 * @date 2019/9/16
 **/
@Mapper
public interface CheckItemDao {
    /**
     * 插入
     * @param checkItem
     */
    void insert(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByConditon(@Param("queryString") String queryString);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteById(@Param("id") Integer id);

    /**
     * 更新
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    CheckItem selectById(@Param("id") Integer id);
}
