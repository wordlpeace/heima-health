package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

=======
import org.apache.ibatis.annotations.Param;

import java.util.List;

>>>>>>> team_work_finish
/**
 * @author zhangmeng
 * @description 套餐DAO
 * @date 2019/9/26
 **/
<<<<<<< HEAD
@Mapper
=======
>>>>>>> team_work_finish
public interface SetMealDao {
    /**
     * 插入
     * @param setmeal
     */
    void insert(Setmeal setmeal);

    /**
     * 插入与检查组关联关系
     * @param setmealId
     * @param checkgroupId
     */
    void insertSetMealAndCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 根据条件分页查询
     * @param queryString
     * @return
     */
    Page<Setmeal> selectByCondition(@Param("queryString") String queryString);
<<<<<<< HEAD
=======

    /**
     * 根据图片统计
     * @param img
     * @return
     */
    long countByImg(@Param("img") String img);

    /**
     * 查询所有
     * @return
     */
    List<Setmeal> selectAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Setmeal selectById(@Param("id") Integer id);
>>>>>>> team_work_finish
}
