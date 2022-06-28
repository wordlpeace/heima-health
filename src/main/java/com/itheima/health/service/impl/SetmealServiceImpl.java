package com.itheima.health.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.Setmealdao;
import com.itheima.health.dao.checkgroupdao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private Setmealdao setmealdao;


    @Transactional
    @Override
    public void add(Setmeal setmeal ,String checkgroupIds) {
        setmealdao.insert(setmeal);
        Setmeal findbyname = setmealdao.findbyname(setmeal.getName());
        String[] split = checkgroupIds.split(",");
        for (String s : split) {
        setmealdao.addrelationtable(findbyname.getId(), Integer.valueOf(s));
        }
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
      PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        Page<Setmeal> checkGroups = setmealdao.selectByConditon(queryPageBean.getQueryString());

        return new PageResult(checkGroups.getTotal(),checkGroups.getResult());


    }
}
