package com.itheima.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.SetMealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
=======
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
>>>>>>> team_work_finish

/**
 * @author zhangmeng
 * @description 套餐SEVICE实现类
 * @date 2019/9/26
 **/
@Service
@Slf4j
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        log.info("[套餐-添加]data:{},checkgroupIds:{}",setmeal,checkgroupIds);
        // 调用DAO数据入库
        // 插入基本数据
        setMealDao.insert(setmeal);
        // 插入关联数据
        for (Integer checkgroupId : checkgroupIds) {
            setMealDao.insertSetMealAndCheckGroup(setmeal.getId(),checkgroupId);
        }

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //设置分页参数
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //DAO查询数据
        Page<Setmeal> page = setMealDao.selectByCondition(queryPageBean.getQueryString());
        //封装返回值
        return new PageResult(page.getTotal(),page.getResult());
    }
<<<<<<< HEAD
=======

    @Override
    public long countByImg(String img) {
        return setMealDao.countByImg(img);
    }

    @Override
    public List<Setmeal> findAll() {
        //调用DAO查询所有
        return setMealDao.selectAll();
    }

    @Override
    public Setmeal findById(Integer id) {
        //调用DAO查询数据
        return setMealDao.selectById(id);
    }
>>>>>>> team_work_finish
}
